<script>
	import { auth } from "$lib/firebase.js";
	import { onMount } from 'svelte';
	import { writable } from 'svelte/store';
	import { saveIncomeToBackend, saveExpenseToBackend } from '$lib/api.js';
	import { fetchUserData } from "$lib/firestore.js"; // Import from firestore.js
	import Modal from '$lib/components/Modal.svelte';
	import { transformDataToTransactions} from "$lib/transactionHelper.js";
	import Messages from "$lib/components/Messages.svelte";

	export let uid = writable(null); // Store the UID for use in API calls

	let currentView = "dashboard"; // Default view

	function setView(view) {
		currentView = view;
		console.log(currentView)
	}

	let isIncomeModalOpen = false;
	let isExpenseModalOpen = false;

	const openIncomeModal = () => {
		isIncomeModalOpen = true;
	};

	const openExpenseModal = () => {
		isExpenseModalOpen = true;
	};

	const closeIncomeModal = () => {
		isIncomeModalOpen = false;
	};

	const closeExpenseModal = () => {
		isExpenseModalOpen = false;
	};
	const saveIncome = async (income) => {
		try {
			const user = auth.currentUser;

			if (!user) {
				throw new Error("User is not authenticated");
			}

			const payload = {
				...income,
				userId: user.uid,
			};

			console.log("Sending income payload:", payload);

			// Check if the income is recurrent and call the appropriate method
			let response;
			if (income.isRecurrent) {
				response = await saveRecurrentIncomeToBackend(payload);
				console.log("Recurrent income saved successfully. Backend response:", response);
			} else {
				response = await saveIncomeToBackend(payload);
				console.log("Income saved successfully. Backend response:", response);
			}

			closeIncomeModal();

			// Fetch updated user data
			const dates = getLastThreeMonths();
			const updatedData = await fetchUserData(user.uid, dates);
			transactions = transformDataToTransactions(updatedData.income, updatedData.spending);

			let new_income = updatedData.income;

			console.log("Updated income:", new_income);
		} catch (error) {
			console.error("Failed to save income:", error);
			alert("Failed to save income. Please try again.");
		}
	};


	const saveExpense = async (expense) => {
		try {
			const user = auth.currentUser;

			if (!user) {
				throw new Error("User is not authenticated");
			}

			const payload = {
				...expense,
				userId: user.uid,
			};

			console.log("Sending expense payload:", payload);

			// Check if the expense is recurrent and call the appropriate method
			let response;
			if (expense.isRecurrent) {
				response = await saveRecurrentExpenseToBackend(payload);
				console.log("Recurrent expense saved successfully. Backend response:", response);
			} else {
				response = await saveExpenseToBackend(payload);
				console.log("Expense saved successfully. Backend response:", response);
			}

			closeExpenseModal();

			// Fetch updated user data
			const dates = getLastThreeMonths();
			const updatedData = await fetchUserData(user.uid, dates);
			transactions = transformDataToTransactions(updatedData.income, updatedData.spending);

			let spending = updatedData.spending; // Update spending and income
			console.log("Updated spending:", spending);
		} catch (error) {
			console.error("Failed to save expense:", error);
			alert("Failed to save expense. Please try again.");
		}
	};



	// Function to log the user out
	async function logout() {
		try {
			await auth.signOut();
			console.log("User logged out successfully");
			// Optionally, redirect the user to a login page or update the UI
			window.location.href = "/login"; // Adjust the URL as needed
		} catch (error) {
			console.error("Error logging out:", error);
		}
	}

	let email = null;
	let isDropdownOpen = false;

	// Toggle dropdown visibility
	function toggleDropdown() {
		isDropdownOpen = !isDropdownOpen;
	}

	let transactions = [
		{ id: 1, name: "Grocery Shopping", category: "Food", date: "2024-11-01", amount: -50 },
		{ id: 2, name: "Monthly Rent", category: "Rent", date: "2024-11-01", amount: -1200 },
		{ id: 3, name: "Salary", category: "Income", date: "2024-11-03", amount: 3000 },
		{ id: 4, name: "Freelance Work", category: "Income", date: "2024-11-05", amount: 500 },
		{ id: 5, name: "Gym Membership", category: "Health", date: "2024-11-06", amount: -40 },
		{ id: 6, name: "Utility Bill", category: "Utilities", date: "2024-11-08", amount: -100 },
		{ id: 7, name: "Car Insurance", category: "Insurance", date: "2024-11-10", amount: -200 },
		{ id: 8, name: "Coffee Shop", category: "Food", date: "2024-11-12", amount: -10 },
		{ id: 9, name: "Bonus", category: "Income", date: "2024-11-14", amount: 1000 },
		{ id: 10, name: "Electricity Bill", category: "Utilities", date: "2024-11-15", amount: -150 },
	];

	// Helper function to parse MMYY formatted dates
	function parseDate(mmYY) {
		const month = parseInt(mmYY.slice(0, 2), 10) - 1; // Convert MM to zero-based month
		const year = parseInt(`20${mmYY.slice(2, 4)}`, 10); // Convert YY to full year
		return new Date(year, month, 1); // Assume the 1st of the month
	}

	let searchQuery = ""; // Reactive search query variable

	// Current date information
	let currentMonth = new Date().getMonth(); // Current zero-based month
	let currentYear = new Date().getFullYear(); // Current year

	// Generate a readable "Month Year" string
	const currentMonthAndYear = new Date().toLocaleString('default', {
		month: 'long',
		year: 'numeric',
	});

	// Reactive: Filter transactions for the current month
	$: monthlyTransactions = transactions.filter((t) => {
		const transactionDate = parseDate(t.date);
		return (
				transactionDate.getMonth() === currentMonth &&
				transactionDate.getFullYear() === currentYear
		);
	});

	// Reactive: Calculate total income
	$: totalIncome = monthlyTransactions
			.filter((t) => t.amount > 0)
			.reduce((sum, t) => sum + t.amount, 0);

	// Reactive: Calculate total expenses
	$: totalExpenses = monthlyTransactions
			.filter((t) => t.amount < 0)
			.reduce((sum, t) => sum + Math.abs(t.amount), 0);

	// Reactive: Calculate net profit
	$: netProfit = totalIncome - totalExpenses;

	// Reactive: Find top spending category
	$: categorySpendMap = monthlyTransactions
			.filter((t) => t.amount < 0)
			.reduce((map, t) => {
				const category = t.category || "Uncategorized"; // Handle missing categories
				map[category] = (map[category] || 0) + Math.abs(t.amount);
				return map;
			}, {});

	// Reactive: Determine the most expensive category
	$: [mostExpensiveCategory, categorySpend] = Object.entries(categorySpendMap).reduce(
			([topCategory, maxSpend], [category, spend]) =>
					spend > maxSpend ? [category, spend] : [topCategory, maxSpend],
			["N/A", 0] // Default value when no spending exists
	);

	let activeTab = "expense"; // Default tab
	let sortOption = "date"; // Default sort
	let selectedCategories = new Set(); // Stores selected categories

	let predefinedCategories = [
		"Food",
		"Rent",
		"Health",
		"Utilities",
		"Insurance",
		"Miscellaneous"
	];

	// Extract unique categories
	let categories = [...predefinedCategories];

	// Reactive filtered transactions
	$: filteredTransactions = [...transactions]
			.filter((t) => t.name.toLowerCase().includes(searchQuery.toLowerCase())) // Filter by search query
			.sort((a, b) => {
				if (sortOption === "amount") {
					return Math.abs(b.amount) - Math.abs(a.amount); // Sort by absolute amount (largest to smallest)
				} else if (sortOption === "date") {
					return new Date(b.date) - new Date(a.date); // Sort by date
				}
			})
			.filter(
					(t) =>
							(activeTab === "expense" ? t.amount < 0 : t.amount > 0) &&
							(selectedCategories.size === 0 || selectedCategories.has(t.category))
			);

	// Toggle category filter reactively
	function toggleCategory(category) {
		if (selectedCategories.has(category)) {
			selectedCategories = new Set([...selectedCategories].filter((c) => c !== category));
		} else {
			selectedCategories = new Set([...selectedCategories, category]);
		}
	}


	onMount(() => {
		const unsubscribe = auth.onAuthStateChanged(async (user) => {
			if (user) {
				email = user.email;
				uid.set(user.uid);

				try {

					const lastThreeMonths = getLastThreeMonths();
					const result = await fetchUserData(user.uid, lastThreeMonths);
					transactions = transformDataToTransactions(result.income, result.spending);
					console.log(transactions)

				} catch (err) {
					console.error("Error fetching user data:", err);
				} finally {
					console.log('fetched!')
				}
			} else {
				window.location.href = "/login";
			}
		});

		return () => unsubscribe();
	});

	function getLastThreeMonths() {
		const months = [];
		const now = new Date();

		for (let i = 0; i < 3; i++) {
			const date = new Date(now.getFullYear(), now.getMonth() - i, 1);
			const mm = String(date.getMonth() + 1).padStart(2, "0");
			const yy = String(date.getFullYear()).slice(-2);
			months.push(mm + yy);
		}

		return months;
	}

	// Helper function to define category colors
	function getCategoryColor(category) {
		const categoryColors = {
			Food: "#FF5733",
			Rent: "#FFC300",
			Entertainment: "#DAF7A6",
			Health: "#FF33FF",
			Utilities: "#33C3FF",
			Insurance: "#FF5733",
			Income: "#4CAF50",
		};
		return categoryColors[category] || "#CCCCCC"; // Default color if category not found
	}

	$: spendingSummary = `
		Here is your current financial summary: Total Income: $${totalIncome.toLocaleString()}Total Expenses: $${totalExpenses.toLocaleString()}Net Profit: $${netProfit.toLocaleString()} Top Spending Category: ${mostExpensiveCategory} ($${categorySpend.toLocaleString()})
	`;
</script>

<div class="min-h-full">


	<!-- Static sidebar for desktop -->
	<div class="hidden lg:fixed lg:inset-y-0 lg:flex lg:w-64 lg:flex-col lg:border-r lg:border-gray-200 lg:bg-gray-100 lg:pb-4 lg:pt-5">
		<div class="flex shrink-0 items-center px-6">
			<img class="h-8 w-auto mr-3" src="/logo.svg" alt="Your Company">
			<span class="text-2xl font-semibold text-gray-900">SpendSmart</span>
		</div>

		<!-- Sidebar component, swap this element with another sidebar if you like -->
		<div class="mt-5 flex h-0 flex-1 flex-col overflow-y-auto pt-1">
			<!-- User account dropdown -->
			<div class="relative inline-block px-3 text-left">
				<div>
					<button type="button" class="group w-full rounded-md bg-gray-100 px-3.5 py-2 text-left text-sm font-medium text-gray-700 hover:bg-gray-200 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-gray-100" id="options-menu-button" aria-expanded="false" aria-haspopup="true">
            <span class="flex w-full items-center justify-between">
              <span class="flex min-w-0 items-center justify-between space-x-3">
                <img class="size-10 shrink-0 rounded-full bg-gray-300" src="/github.svg" alt="">
                <span class="flex min-w-0 flex-1 flex-col">
                  <span class="truncate text-sm font-medium text-gray-900">{email}</span>
                  <span class="truncate text-sm text-gray-500">@{email}</span>
                </span>
              </span>
            </span>
					</button>
				</div>
			</div>
			<!-- Sidebar Search -->
			<div class="mt-5 px-3">
				<label for="search" class="sr-only">Search Transactions</label>
				<div class="relative mt-1 rounded-md shadow-sm">
					<div class="pointer-events-none absolute inset-y-0 left-0 flex items-center pl-3" aria-hidden="true">
						<svg class="size-4 text-gray-400" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true" data-slot="icon">
							<path fill-rule="evenodd" d="M9 3.5a5.5 5.5 0 1 0 0 11 5.5 5.5 0 0 0 0-11ZM2 9a7 7 0 1 1 12.452 4.391l3.328 3.329a.75.75 0 1 1-1.06 1.06l-3.329-3.328A7 7 0 0 1 2 9Z" clip-rule="evenodd" />
						</svg>
					</div>
					<input
						type="text"
						name="search"
						id="search"
						class="block w-full rounded-md border-0 py-1.5 pl-9 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm"
						placeholder="Search Transactions"
						bind:value="{searchQuery}"
					/>				</div>
			</div>
			<!-- Navigation -->
			<nav class="mt-6 px-3">
				<div class="space-y-1">
					<!-- Dashboard -->
					<a
							href="#"
							on:click={() => setView("dashboard")}
							class="group flex items-center rounded-md px-2 py-2 text-sm font-medium
            {currentView === 'dashboard' ? 'bg-gray-200 text-gray-900' : 'text-gray-700 hover:bg-gray-50 hover:text-gray-900'}"
					>
						<svg
								class="mr-3 size-6 shrink-0
                {currentView === 'dashboard' ? 'text-gray-500' : 'text-gray-400 group-hover:text-gray-500'}"
								fill="none"
								viewBox="0 0 24 24"
								stroke-width="1.5"
								stroke="currentColor"
								aria-hidden="true"
								data-slot="icon"
						>
							<path
									stroke-linecap="round"
									stroke-linejoin="round"
									d="m2.25 12 8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25"
							/>
						</svg>
						Dashboard
					</a>

					<!-- AI Advisor -->
					<a
							href="#"
							on:click={() => setView("advisor")}
							class="group flex items-center rounded-md px-2 py-2 text-sm font-medium
            {currentView === 'advisor' ? 'bg-gray-200 text-gray-900' : 'text-gray-700 hover:bg-gray-50 hover:text-gray-900'}"
					>
						<svg
								class="mr-3 size-6 shrink-0
                {currentView === 'advisor' ? 'text-gray-500' : 'text-gray-400 group-hover:text-gray-500'}"
								fill="none"
								viewBox="0 0 24 24"
								stroke-width="1.5"
								stroke="currentColor"
								aria-hidden="true"
								data-slot="icon"
						>
							<path
									stroke-linecap="round"
									stroke-linejoin="round"
									d="M3.75 5.25h16.5m-16.5 4.5h16.5m-16.5 4.5h16.5m-16.5 4.5h16.5"
							/>
						</svg>
						AI Advisor
					</a>

					<!-- Recent Stats -->
					<a
							href="#"
							on:click={() => setView("stats")}
							class="group flex items-center rounded-md px-2 py-2 text-sm font-medium
            {currentView === 'stats' ? 'bg-gray-200 text-gray-900' : 'text-gray-700 hover:bg-gray-50 hover:text-gray-900'}"
					>
						<svg
								class="mr-3 size-6 shrink-0
                {currentView === 'stats' ? 'text-gray-500' : 'text-gray-400 group-hover:text-gray-500'}"
								fill="none"
								viewBox="0 0 24 24"
								stroke-width="1.5"
								stroke="currentColor"
								aria-hidden="true"
								data-slot="icon"
						>
							<path
									stroke-linecap="round"
									stroke-linejoin="round"
									d="M12 6v6h4.5m4.5 0a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"
							/>
						</svg>
						Recent Stats
					</a>
				</div>
				<div class="mt-8">
					<h3 class="px-3 text-sm font-medium text-gray-500" id="desktop-teams-headline">Filter by Categories</h3>
					<div class="mt-1 space-y-1" role="group" aria-labelledby="desktop-teams-headline">
						{#each categories as category}
							<div class="flex items-center px-3 py-2">
					<span
						class="mr-4 size-2.5 rounded-full"
						style="background-color: {getCategoryColor(category)}"
						aria-hidden="true"
					></span>
								<input
									type="checkbox"
									id="{category}"
									on:change="{() => toggleCategory(category)}"
									class="size-4 rounded border-gray-300 text-indigo-600 focus:ring-indigo-600"
								/>
								<label for="{category}" class="ml-2 text-sm font-medium text-gray-900">{category}</label>
							</div>
						{/each}
					</div>
				</div>
			</nav>
		</div>
	</div>

	<!-- Main column -->
	<div class="flex flex-col lg:pl-64">
		<!-- Search header -->
		<div class="sticky top-0 z-10 flex h-16 shrink-0 border-b border-gray-200 bg-white lg:hidden">
			<!-- Sidebar toggle, controls the 'sidebarOpen' sidebar state. -->
			<button type="button" class="border-r border-gray-200 px-4 text-gray-500 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-purple-500 lg:hidden">
				<span class="sr-only">Open sidebar</span>
				<svg class="size-6" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" data-slot="icon">
					<path stroke-linecap="round" stroke-linejoin="round" d="M3.75 6.75h16.5M3.75 12H12m-8.25 5.25h16.5" />
				</svg>
			</button>
			<div class="flex flex-1 justify-between px-4 sm:px-6 lg:px-8">
				<div class="flex flex-1">
					<form class="flex w-full md:ml-0" action="#" method="GET">
						<label for="search-field" class="sr-only">Search</label>
						<div class="relative w-full text-gray-400 focus-within:text-gray-600">
							<div class="pointer-events-none absolute inset-y-0 left-0 flex items-center">
								<svg class="size-5" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true" data-slot="icon">
									<path fill-rule="evenodd" d="M9 3.5a5.5 5.5 0 1 0 0 11 5.5 5.5 0 0 0 0-11ZM2 9a7 7 0 1 1 12.452 4.391l3.328 3.329a.75.75 0 1 1-1.06 1.06l-3.329-3.328A7 7 0 0 1 2 9Z" clip-rule="evenodd" />
								</svg>
							</div>
							<input id="search-field" name="search-field" class="block size-full border-transparent py-2 pl-8 pr-3 text-gray-900 focus:border-transparent focus:outline-none focus:ring-0 focus:placeholder:text-gray-400 sm:text-sm" placeholder="Search" type="search">
						</div>
					</form>
				</div>
				<div class="flex items-center">
					<!-- Profile dropdown -->
					<div class="relative ml-3">
						<div>
							<button type="button" class="relative flex max-w-xs items-center rounded-full bg-white text-sm focus:outline-none focus:ring-2 focus:ring-purple-500 focus:ring-offset-2" id="user-menu-button" aria-expanded="false" aria-haspopup="true">
								<span class="absolute -inset-1.5"></span>
								<span class="sr-only">Open user menu</span>
								<img class="size-8 rounded-full" src="https://images.unsplash.com/photo-1502685104226-ee32379fefbe?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80" alt="">
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<main class="flex-1">
			<!-- Page title & actions -->
			<div class="border-b border-gray-200 px-4 py-4 sm:flex sm:items-center sm:justify-between sm:px-6 lg:px-8">
				<div class="min-w-0 flex-1">
					<h1 class="text-lg/6 font-medium text-gray-900 sm:truncate">Welcome back, {email}</h1>
				</div>
				<div class="mt-4 flex sm:ml-4 sm:mt-0">

					<button type="button" class="sm:order-0 order-1 ml-3 inline-flex items-center rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50 sm:ml-0">
						<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6 mr-1">
						<path stroke-linecap="round" stroke-linejoin="round" d="M10.343 3.94c.09-.542.56-.94 1.11-.94h1.093c.55 0 1.02.398 1.11.94l.149.894c.07.424.384.764.78.93.398.164.855.142 1.205-.108l.737-.527a1.125 1.125 0 0 1 1.45.12l.773.774c.39.389.44 1.002.12 1.45l-.527.737c-.25.35-.272.806-.107 1.204.165.397.505.71.93.78l.893.15c.543.09.94.559.94 1.109v1.094c0 .55-.397 1.02-.94 1.11l-.894.149c-.424.07-.764.383-.929.78-.165.398-.143.854.107 1.204l.527.738c.32.447.269 1.06-.12 1.45l-.774.773a1.125 1.125 0 0 1-1.449.12l-.738-.527c-.35-.25-.806-.272-1.203-.107-.398.165-.71.505-.781.929l-.149.894c-.09.542-.56.94-1.11.94h-1.094c-.55 0-1.019-.398-1.11-.94l-.148-.894c-.071-.424-.384-.764-.781-.93-.398-.164-.854-.142-1.204.108l-.738.527c-.447.32-1.06.269-1.45-.12l-.773-.774a1.125 1.125 0 0 1-.12-1.45l.527-.737c.25-.35.272-.806.108-1.204-.165-.397-.506-.71-.93-.78l-.894-.15c-.542-.09-.94-.56-.94-1.109v-1.094c0-.55.398-1.02.94-1.11l.894-.149c.424-.07.765-.383.93-.78.165-.398.143-.854-.108-1.204l-.526-.738a1.125 1.125 0 0 1 .12-1.45l.773-.773a1.125 1.125 0 0 1 1.45-.12l.737.527c.35.25.807.272 1.204.107.397-.165.71-.505.78-.929l.15-.894Z" />
						<path stroke-linecap="round" stroke-linejoin="round" d="M15 12a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
					</svg>Settings</button>
					<button type="button"	on:click="{logout}" class="order-0 inline-flex items-center rounded-md bg-red-500 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-red-400 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:bg-red-500 sm:order-1 sm:ml-3">Logout</button>
				</div>
			</div>

			<div class="mt-7">
				<div class="px-4 sm:px-6">
					<h2 class="text-2xl font-medium text-gray-900">
						Your Spending for <span class="text-[#4784B8]">{currentMonthAndYear}</span>
					</h2>
				</div>

				<dl class="mx-auto grid grid-cols-1 gap-px bg-gray-900/5 sm:grid-cols-2 lg:grid-cols-4">
					<!-- Total Revenue -->
					<div class="flex flex-wrap items-baseline justify-between gap-x-4 gap-y-2 bg-white px-4 py-10 sm:px-6 xl:px-8">
						<dt class="text-sm font-medium text-gray-500">Total Revenue (This Month)</dt>
						<dd class="text-xs font-medium text-green-600">+{Math.round((totalIncome / 10000) * 100)}%</dd>
						<dd class="w-full flex-none text-3xl font-medium tracking-tight text-gray-900">${totalIncome.toLocaleString()}</dd>
					</div>

					<!-- Total Expenses -->
					<div class="flex flex-wrap items-baseline justify-between gap-x-4 gap-y-2 bg-white px-4 py-10 sm:px-6 xl:px-8">
						<dt class="text-sm font-medium text-gray-500">Total Expenses (This Month)</dt>
						<dd class="text-xs font-medium text-rose-600">{Math.round((Math.abs(totalExpenses) / 5000) * 100)}%</dd>
						<dd class="w-full flex-none text-3xl font-medium tracking-tight text-gray-900">${Math.abs(totalExpenses).toLocaleString()}</dd>
					</div>

					<!-- Net Profit -->
					<div class="flex flex-wrap items-baseline justify-between gap-x-4 gap-y-2 bg-white px-4 py-10 sm:px-6 xl:px-8">
						<dt class="text-sm font-medium text-gray-500">Net Gain (This Month)</dt>
						<dd
							class="text-xs font-medium"
							class:class="{netProfit >= 0 ? 'text-green-600' : 'text-rose-600'}"
						>
							{netProfit >= 0 ? `+${(netProfit / totalIncome * 100).toFixed(2)}%` : `${(netProfit / totalIncome * 100).toFixed(2)}%`}
						</dd>
						<dd class="w-full flex-none text-3xl font-medium tracking-tight text-gray-900">${netProfit.toLocaleString()}</dd>
					</div>

					<!-- Top Spending Category -->
					<div class="flex flex-wrap items-baseline justify-between gap-x-4 gap-y-2 bg-white px-4 py-10 sm:px-6 xl:px-8">
						<dt class="text-sm font-medium text-gray-500">Top Spending Category (This Month)</dt>
						<dd class="text-xs font-medium text-gray-700">{mostExpensiveCategory}</dd>
						<dd class="w-full flex-none text-3xl font-medium tracking-tight text-gray-900">${categorySpend.toLocaleString()}</dd>
					</div>
				</dl>
			</div>

			{#if currentView == "dashboard"}
			<div class="mt-2">
				<div class="flex justify-between items-center px-4 sm:px-6">
					<h2 class="text-xl font-medium text-gray-900">Spending History</h2>
					<div class="space-x-2">
						<button
							class="rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50"
							on:click={openIncomeModal}
						>
							Add Income
						</button>
						<button
							class="rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50"
							on:click={openExpenseModal}
						>
							Add Expense
						</button>
					</div>
				</div>
				<ul role="list" class="mt-3 divide-y divide-gray-100 border-t border-gray-200">
					<!-- Add spending history here -->
				</ul>
			</div>

			<Modal
				title="Add Income"
				isOpen={isIncomeModalOpen}
				onClose={closeIncomeModal}
				onSave={saveIncome}
				{categories}
			/>

			<Modal
				title="Add Expense"
				isOpen={isExpenseModalOpen}
				onClose={closeExpenseModal}
				onSave={saveExpense}
				{categories}
			/>

			<div class="container mx-auto px-6 py-4">
				<!-- Tabs -->
				<div class="flex justify-between items-center mb-4">
					<div class="flex space-x-4">
						<button
							type="button"
							class="{activeTab === 'expense' ? 'bg-gray-100 text-gray-900' : 'bg-white text-gray-500'} rounded-md px-3 py-2 text-sm font-semibold shadow-sm ring-1 ring-gray-300 hover:bg-gray-50"
							on:click="{() => (activeTab = 'expense')}"
						>
							Expenses
						</button>
						<button
							type="button"
							class="{activeTab === 'income' ? 'bg-gray-100 text-gray-900' : 'bg-white text-gray-500'} rounded-md px-3 py-2 text-sm font-semibold shadow-sm ring-1 ring-gray-300 hover:bg-gray-50"
							on:click="{() => (activeTab = 'income')}"
						>
							Income
						</button>
					</div>
					<!-- Sort Dropdown -->
					<div class="relative">
						<!-- Dropdown Trigger -->
						<button
							type="button"
							class="group inline-flex justify-center rounded-md bg-white px-4 py-2 text-sm font-medium text-gray-700 shadow-sm ring-1 ring-gray-300 hover:bg-gray-50"
							aria-expanded="{isDropdownOpen}"
							aria-haspopup="true"
							on:click="{toggleDropdown}"
						>
							Sort by: {sortOption === "date" ? "Date" : "Amount"}
							<svg
								class="-mr-1 ml-2 h-5 w-5 text-gray-400 group-hover:text-gray-500"
								viewBox="0 0 20 20"
								fill="currentColor"
								aria-hidden="true"
							>
								<path
									fill-rule="evenodd"
									d="M5.23 7.23a.75.75 0 011.06 0L10 10.94l3.72-3.72a.75.75 0 111.06 1.06l-4.25 4.25a.75.75 0 01-1.06 0L5.23 8.29a.75.75 0 010-1.06z"
									clip-rule="evenodd"
								/>
							</svg>
						</button>

						<!-- Dropdown Menu -->
						{#if isDropdownOpen}
							<div
								class="absolute right-0 mt-2 w-40 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black/5 z-10"
								role="menu"
								aria-orientation="vertical"
								tabindex="-1"
							>
								<div class="py-1" role="none">
									<button
										class="block w-full px-4 py-2 text-left text-sm text-gray-700 hover:bg-gray-100"
										on:click="{() => { sortOption = 'date'; isDropdownOpen = false; }}"
									>
										Date
									</button>
									<button
										class="block w-full px-4 py-2 text-left text-sm text-gray-700 hover:bg-gray-100"
										on:click="{() => { sortOption = 'amount'; isDropdownOpen = false; }}"
									>
										Amount
									</button>
								</div>
							</div>
						{/if}
					</div>
				</div>

				<!-- Transactions Table -->
				<div class="overflow-hidden shadow ring-1 ring-black ring-opacity-5 rounded-lg">
					<table class="min-w-full divide-y divide-gray-300">
						<thead class="bg-gray-50">
						<tr>
							<th scope="col" class="px-6 py-3 text-left text-sm font-medium text-gray-700">Name</th>
							<th scope="col" class="px-6 py-3 text-left text-sm font-medium text-gray-700">Category</th>
							<th scope="col" class="px-6 py-3 text-right text-sm font-medium text-gray-700">Date</th>
							<th scope="col" class="px-6 py-3 text-right text-sm font-medium text-gray-700">Amount</th>
						</tr>
						</thead>
						<tbody class="divide-y divide-gray-200 bg-white">
						{#each filteredTransactions as transaction}
							<tr>
								<td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{transaction.name}</td>
								<td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
									<span style="color: {getCategoryColor(transaction.category)}">●</span> {transaction.category}
								</td>

								<td class="px-6 py-4 whitespace-nowrap text-sm text-right text-gray-500">{transaction.date}</td>
								<td class="px-6 py-4 whitespace-nowrap text-sm text-right font-medium text-gray-900">
									${Math.abs(transaction.amount).toFixed(2)}
								</td>
							</tr>
						{/each}
						</tbody>
					</table>
				</div>
			</div>
				{:else if currentView = "advisor"}
					<Messages {spendingSummary}></Messages>
				{/if}
		</main>
	</div>
</div>
