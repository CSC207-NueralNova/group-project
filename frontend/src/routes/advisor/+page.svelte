<script>
import { auth } from "$lib/firebase.js";
import { onMount } from 'svelte';
import { fetchUserData } from "$lib/firestore.js"; // Import from firestore.js
import { transformDataToTransactions} from "$lib/transactionHelper.js";


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
let transactions = [];

onMount(() => {
	const unsubscribe = auth.onAuthStateChanged(async (user) => {
		if (user) {
			email = user.email;

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

			<!-- Navigation -->
			<nav class="mt-6 px-3">
				<div class="space-y-1">
					<!-- Current: "bg-gray-200 text-gray-900", Default: "text-gray-700 hover:bg-gray-50 hover:text-gray-900" -->
					<a href="/dashboard" class="group flex items-center rounded-md bg-gray-200 px-2 py-2 text-sm font-medium text-gray-900" aria-current="page">
						<!-- Current: "text-gray-500", Default: "text-gray-400 group-hover:text-gray-500" -->
						<svg class="mr-3 size-6 shrink-0 text-gray-500" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" data-slot="icon">
							<path stroke-linecap="round" stroke-linejoin="round" d="m2.25 12 8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25" />
						</svg>
						Dashboard
					</a>
					<a href="/advisor" class="group flex items-center rounded-md px-2 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50 hover:text-gray-900">
						<svg class="mr-3 size-6 shrink-0 text-gray-400 group-hover:text-gray-500" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" data-slot="icon">
							<path stroke-linecap="round" stroke-linejoin="round" d="M3.75 5.25h16.5m-16.5 4.5h16.5m-16.5 4.5h16.5m-16.5 4.5h16.5" />
						</svg>
						AI Advisor
					</a>
					<a href="#" class="group flex items-center rounded-md px-2 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50 hover:text-gray-900">
						<svg class="mr-3 size-6 shrink-0 text-gray-400 group-hover:text-gray-500" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" data-slot="icon">
							<path stroke-linecap="round" stroke-linejoin="round" d="M12 6v6h4.5m4.5 0a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
						</svg>
						Recent Stats
					</a>
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
	</div>
</div>
