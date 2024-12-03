<script>
	// Import necessary stores and functions
	import { writable } from 'svelte/store';
	import { chatbotRequest } from '$lib/api';
	import DOMPurify from "dompurify";
	import {marked} from "marked";

	export let spendingSummary
	let loading = false

	// Function to safely parse and sanitize the response
	function formatResponse(response) {
		const rawHTML = marked.parse(response); // Convert Markdown to HTML
		return DOMPurify.sanitize(rawHTML); // Sanitize the HTML
	}

	// Advisor-related data
	const avatars = {
		"Finance Expert": "/finance.png",
		"Stocks Expert": "/stocks.png",
		"Tax Advisor": "/tax.png",
		"User": "/pfp.jpg"
	};

	const advisorInitialPrompts = {
		"Finance Expert": [
			"How can I start investing?",
			"What are effective budgeting strategies?",
			"How can I save more efficiently?"
		],
		"Stocks Expert": [
			"How can I get started in stock trading?",
			"What are the best strategies for portfolio balancing?",
			"How do I assess stock risks?"
		],
		"Tax Advisor": [
			"How can I maximize my tax deductions?",
			"Are there tax-saving options for self-employed?",
			"What should I know about tax compliance?"
		]
	};

	const advisorDescriptions = {
		"Finance Expert": "An expert in financial planning, guiding you in managing investments, budgeting, and achieving long-term wealth.",
		"Stocks Expert": "A seasoned stocks expert offering analysis on trading strategies, portfolio balancing, and investment risks in stock markets.",
		"Tax Advisor": "Your tax specialist, assisting with tax-saving strategies, compliance, and optimizing tax benefits for better financial health."
	};

	let selectedAdvisor = 'Finance Expert';
	let prompts = advisorInitialPrompts[selectedAdvisor];
	let advisorDescription = advisorDescriptions[selectedAdvisor]; // Dynamic description
	let showAdvisorList = false; // Tracks whether the advisor dropdown is visible
	let isWaitingForResponse = false;

	// Messages store for chat messages
	let messages = writable([]);

	// Function to send a message to the backend and handle response
	async function sendMessage(prompt) {
		if (isWaitingForResponse) return;

		// Add user's message to the chat
		messages.update((msgs) => [
			...msgs,
			{
				sender: "You",
				avatar: avatars["User"],
				text: prompt,
				timestamp: new Date().toISOString(),
				isAI: false
			}
		]);

		isWaitingForResponse = true;

		try {
			const sanitizeInput = (input) => {
				return input
						.replace(/\\/g, "\\\\") // Escape backslashes
						.replace(/\n/g, "\\n") // Escape newlines
						.replace(/"/g, '\\"'); // Escape double quotes
			};

			const sanitizedPrompt = sanitizeInput(prompt);
			const sanitizedSummary = sanitizeInput(spendingSummary);
			loading = true
			const data = await chatbotRequest(
								selectedAdvisor,
								`You are a ${selectedAdvisor}. Your role is to assist the user with personalized financial advice. The user's financial summary is as follows: ${sanitizedSummary} The user has the following question:"${sanitizedPrompt}"`);
			// Simulate response delay
			setTimeout(() => {
				messages.update((msgs) => [
					...msgs,
					{
						sender: selectedAdvisor,
						avatar: avatars[selectedAdvisor],
						text: data.response,
						timestamp: new Date().toISOString(),
						isAI: true
					}
				]);
				isWaitingForResponse = false;
			}, 1000);
			loading = false
		} catch (error) {
			console.error("Error fetching chatbot response:", error);
			isWaitingForResponse = false;
			loading = false
		}
	}

	// Function to select an advisor and update prompts and description
	function selectAdvisor(advisor) {
		selectedAdvisor = advisor;
		prompts = advisorInitialPrompts[advisor];
		advisorDescription = advisorDescriptions[advisor]; // Update description
		showAdvisorList = false; // Hide the dropdown after selection
	}
</script>

<style>
	.dashboard-container {
		display: flex;
		flex-direction: column;
		height: 65vh;
		background-color: #f9fafb;
		padding: 1rem;
	}

	.chat-box-container {
		flex-grow: 1;
		overflow-y: auto;
		border: 1px solid #e5e7eb;
		border-radius: 10px;
		background-color: #fff;
		padding: 1rem;
		display: flex;
		flex-direction: column;
	}

	.placeholder {
		text-align: center;
		margin: auto;
	}

	.message-bubble {
		display: inline-block;
		max-width: 70%;
		padding: 0.5rem 1rem;
		border-radius: 10px;
		margin-bottom: 0.5rem;
		animation: fadeIn 0.5s ease forwards;
	}

	.user-message {
		background-color: #e0e7ff;
		color: #000;
		align-self: flex-end;
	}

	.ai-message {
		background-color: #f9fafb;
		color: #000;
		align-self: flex-start;
	}

	.message-row {
		display: flex;
		align-items: flex-start;
		margin-bottom: 1rem;
	}

	.message-row.user {
		flex-direction: row-reverse;
	}

	.message-row img {
		width: 40px;
		height: 40px;
		border-radius: 50%;
		margin: 0 0.5rem;
	}

	.options-container {
		position: sticky;
		bottom: 0;
		background-color: #ffffff; /* White for a cleaner look */
		padding: 1rem;
		box-shadow: 0 -2px 4px rgba(0, 0, 0, 0.05);
	}

	.advisor-selector {
		margin-bottom: 0.5rem;
		display: flex;
		align-items: center;
		gap: 1rem;
	}

	.advisor-description {
		margin-top: 0.5rem;
		font-size: 0.875rem;
		color: #4b5563; /* Tailwind Gray-600 */
	}

	.dropdown-menu {
		position: absolute;
		left: 0;
		background-color: white;
		border: 1px solid #e5e7eb;
		border-radius: 0.5rem;
		box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
		width: 12rem;
		z-index: 10;
		overflow-y: auto;
	}

	.dropdown-menu li {
		display: flex;
		align-items: center;
		padding: 0.5rem;
		cursor: pointer;
		transition: background-color 0.2s ease;
	}

	.dropdown-menu li:hover {
		background-color: #4f46e5;
		color: white;
	}

	.dropdown-menu img {
		width: 30px;
		height: 30px;
		border-radius: 50%;
		margin-right: 0.5rem;
	}

	.prompt-button {
		background-color: #f3f4f6;
		color: #333;
		cursor: pointer;
		transition: background-color 0.2s ease;
	}

	.prompt-button:hover {
		background-color: #e5e7eb;
	}

	.advisor-name {
		text-align: left;
		font-weight: bold;
	}

	.message-row {
		display: flex;
		align-items: flex-start;
		margin-bottom: 1rem;
	}

	.message-row img {
		width: 40px;
		height: 40px;
		border-radius: 50%;
		margin-right: 0.5rem;
	}

	.message-bubble {
		display: inline-block;
		max-width: 70%;
		padding: 0.5rem 1rem;
		border-radius: 10px;
		margin-bottom: 0.5rem;
		animation: fadeIn 0.5s ease forwards;
	}

	.ai-message {
		background-color: #f9fafb;
		color: #000;
		align-self: flex-start;
	}

	.loading-spinner {
		display: flex;
		align-items: center;
		background-color: #f9fafb;
		color: #000;
		padding: 0.5rem 1rem;
		border-radius: 10px;
	}

	.animate-spin {
		animation: spin 1s linear infinite;
		margin-right: 0.5rem;
	}

	@keyframes spin {
		from {
			transform: rotate(0deg);
		}
		to {
			transform: rotate(360deg);
		}
	}


</style>

<div class="dashboard-container">

	<!-- Chat Messages -->
	<!-- Chat Messages -->
	<div class="chat-box-container">
		{#if $messages.length === 0}
			<!-- Placeholder for empty chat -->
			<div class="placeholder">
				<p class="text-gray-600">Begin your conversation with {selectedAdvisor}</p>
			</div>
		{:else}
			<!-- Messages -->
			{#each $messages as message (message.timestamp)}
				<div class="message-row {message.isAI ? '' : 'user'}">
					<!-- Show advisor's name above AI messages -->

					<img src={message.avatar} alt="{message.sender}" />
					<div class="message-bubble {message.isAI ? 'ai-message' : 'user-message'}">
						{@html message.isAI ? formatResponse(message.text) : message.text}
					</div>
				</div>
			{/each}

			<!-- Loading animation for inline response -->
			{#if loading}
				<div class="message-row">
					<img src={avatars[selectedAdvisor]} alt="{selectedAdvisor}" />
					<div class="loading-spinner ai-message">
						<svg
								class="animate-spin h-5 w-5 text-gray-500"
								xmlns="http://www.w3.org/2000/svg"
								fill="none"
								viewBox="0 0 24 24"
						>
							<circle
									class="opacity-25"
									cx="12"
									cy="12"
									r="10"
									stroke="currentColor"
									stroke-width="4"
							></circle>
							<path
									class="opacity-75"
									fill="currentColor"
									d="M4 12a8 8 0 018-8v8H4z"
							></path>
						</svg>
						<p class="text-gray-600">Thinking...</p>
					</div>
				</div>
			{/if}
		{/if}
	</div>


	<!-- Advisor Selector and Prompts -->
	<div class="options-container">
		<div class="advisor-selector">
			<button
					type="button"
					on:click={() => (showAdvisorList = !showAdvisorList)}
					class="w-full flex items-center rounded-md border border-gray-300 bg-white py-2 px-4 text-gray-900 shadow-sm hover:border-gray-400 focus:outline-none sm:text-sm"
			>
				<img src={avatars[selectedAdvisor]} alt="{selectedAdvisor}" class="w-6 h-6 rounded-full mr-2" />
				<span class="truncate">{selectedAdvisor}</span>
				<svg
						xmlns="http://www.w3.org/2000/svg"
						viewBox="0 0 20 20"
						fill="currentColor"
						class="ml-2 w-5 h-5 text-gray-400"
				>
					<path
							fill-rule="evenodd"
							d="M5.22 8.22a.75.75 0 0 1 1.06 0L10 11.94l3.72-3.72a.75.75 0 1 1 1.06 1.06l-4.25 4.25a.75.75 0 0 1-1.06 0L5.22 9.28a.75.75 0 0 1 0-1.06Z"
							clip-rule="evenodd"
					/>
				</svg>
			</button>

			{#if showAdvisorList}
				<ul class="dropdown-menu">
					{#each Object.keys(avatars).filter((advisor) => advisor !== "User") as advisor}
						<li
								class="cursor-pointer px-4 py-2 flex items-center hover:bg-indigo-600 hover:text-white"
								on:click={() => selectAdvisor(advisor)}
						>
							<img src={avatars[advisor]} alt="{advisor}" />
							<span>{advisor}</span>
						</li>
					{/each}
				</ul>
			{/if}
		</div>

		<!-- Advisor Description -->
		<p class="advisor-description">{advisorDescription}</p>

		<div class="flex flex-wrap justify-start gap-2 mt-4">
			{#each prompts as prompt}
				<button
						on:click={() => sendMessage(prompt)}
						class="prompt-button w-auto inline-flex items-center justify-center rounded-lg px-4 py-2 text-sm font-semibold text-gray-800 bg-gray-200 hover:bg-gray-300"
				>
					{prompt}
				</button>
			{/each}
		</div>
	</div>
</div>
