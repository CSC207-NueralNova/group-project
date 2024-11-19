<script>
	import { writable } from 'svelte/store';
	import { chatbotRequest } from '$lib/api';

	const avatars = {
		"Finance Expert": "/finance.png",
		"Stocks Expert": "/stocks.png",
		"Tax Advisor": "/tax.png",
		"User": "/pfp.jpg"
	};

	const advisorDescriptions = {
		"Finance Expert": "An expert in financial planning, guiding you in managing investments, budgeting, and achieving long-term wealth.",
		"Stocks Expert": "A seasoned stocks expert offering analysis on trading strategies, portfolio balancing, and investment risks in stock markets.",
		"Tax Advisor": "Your tax specialist, assisting with tax-saving strategies, compliance, and optimizing tax benefits for better financial health."
	};

	const advisorInitialPrompts = {
		"Finance Expert": [
			"How can I start investing?",
			"What are effective budgeting strategies?",
			"What should I consider for wealth management?",
			"How can I save more efficiently?"
		],
		"Stocks Expert": [
			"How can I get started in stock trading?",
			"What are the best strategies for portfolio balancing?",
			"How do I assess stock risks?",
			"Which stocks should I invest in now?"
		],
		"Tax Advisor": [
			"How can I maximize my tax deductions?",
			"Are there tax-saving options for self-employed?",
			"What should I know about tax compliance?",
			"How can I minimize tax liability?"
		]
	};

	const advisorFollowUps = {
		"How can I start investing?": [
			"What is the minimum amount to start investing?",
			"Should I focus on stocks or mutual funds?",
			"What risks should I be aware of?",
			"Ask more questions"
		],
		"What are effective budgeting strategies?": [
			"How do I track my expenses effectively?",
			"What budgeting tools do you recommend?",
			"How do I balance my budget?",
			"Ask more questions"
		],
	};

	let selectedAdvisor = 'Finance Expert';
	let prompts = advisorInitialPrompts[selectedAdvisor];
	let messages = writable([]);
	let showAdvisorList = false;
	let isWaitingForResponse = false;

	async function sendMessage(prompt) {
		if (isWaitingForResponse) return;

		messages.update((msgs) => [
			...msgs,
			{
				sender: "You",
				avatar: avatars["User"],
				text: prompt,
				timestamp: new Date().toISOString(),
				relativeTime: "Just now",
				isAI: false,
				show: true
			}
		]);

		if (prompt === "Ask more questions") {
			prompts = advisorInitialPrompts[selectedAdvisor];
			return;
		}

		isWaitingForResponse = true;

		try {
			const data = await chatbotRequest("User", prompt);

			// Adding a delay to simulate "thinking" effect
			setTimeout(() => {
				messages.update((msgs) => [
					...msgs,
					{
						sender: selectedAdvisor,
						avatar: avatars[selectedAdvisor],
						text: data.advice,
						timestamp: new Date().toISOString(),
						relativeTime: "Just now",
						isAI: true,
						show: true // Enables fade-in on message display
					}
				]);

				prompts = advisorFollowUps[prompt] || advisorInitialPrompts[selectedAdvisor];
				isWaitingForResponse = false;
			}, 1000); // Adjust the delay duration as desired
		} catch (error) {
			console.error("Error fetching chatbot response:", error);
			isWaitingForResponse = false;
		}
	}

	function selectAdvisor(advisor) {
		selectedAdvisor = advisor;
		prompts = advisorInitialPrompts[advisor];
		showAdvisorList = false;
	}
</script>

<style>
    .chat-box-container {
        display: flex;
        flex-direction: column;
        height: 500px;
    }

    .chat-box {
        flex-grow: 1;
        overflow-y: auto;
        padding: 0.5rem;
    }

    .message-bubble {
        display: inline-block;
        max-width: 70%;
        padding: 0.5rem 1rem;
        border-radius: 10px;
        opacity: 0;
        animation: fadeIn 0.5s ease forwards; /* Apply fade-in effect */
    }

    @keyframes fadeIn {
        to {
            opacity: 1;
        }
    }

    .user-message {
        background-color: #e0e7ff;
        color: #000;
        margin-left: 20px;
    }

    .ai-message {
        background-color: #f3f4f6;
        color: #000;
    }

    .prompt-button {
        background-color: #f3f4f6;
        color: #333;
        cursor: pointer;
        transition: opacity 0.3s ease;
    }
    .prompt-button.waiting {
        opacity: 0.6;
        pointer-events: none;
    }
    .prompt-button:hover:not(.waiting) {
        background-color: #e5e7eb;
    }

    .dropdown-menu {
        position: absolute;
        background-color: white;
        border: 1px solid #ccc;
        border-radius: 0.25rem;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
        width: 100%;
        max-height: 150px;
        overflow-y: auto;
        z-index: 10;
    }

    /* Align the "You" text with the user's profile picture */
    .user-label {
        margin-left: auto;
        margin-right: 10px;
        color: #555;
        font-weight: bold;
        font-size: 0.875rem;
    }
</style>

<div class="flex">
	<div class="chat-box-container overflow-hidden rounded-lg border border-gray-300 shadow-sm flex-1">
		<ul role="list" class="chat-box divide-y divide-gray-100 bg-white">
			{#each $messages as message (message.timestamp)}
				<li class="flex gap-x-4 py-3 {message.isAI ? 'text-left' : 'text-right'} fade-in">
					{#if message.isAI}
						<img class="w-12 h-12 flex-none rounded-full bg-gray-50" src={message.avatar} alt="AI Avatar">
						<div class="flex-auto">
							<div class="flex items-baseline justify-between gap-x-4">
								<p class="text-sm font-semibold text-gray-900">{message.sender}</p>
								<p class="flex-none text-xs text-gray-600">
									<time datetime={message.timestamp}>{message.relativeTime}</time>
								</p>
							</div>
							<p class="ai-message message-bubble mt-1 text-sm text-gray-600">{message.text}</p>
						</div>
					{:else}
						<div class="flex-auto text-right">
							<div class="flex items-center justify-end gap-x-4">
								<p class="user-label">You</p>
								<img class="w-12 h-12 flex-none rounded-full bg-gray-50" src={message.avatar} alt="User Avatar">
							</div>
							<p class="user-message message-bubble mt-1 text-sm">{message.text}</p>
						</div>
					{/if}
				</li>
			{/each}
		</ul>

		<div class="border-t border-gray-200 px-4 py-4">
			<div class="flex space-x-4 mb-3 relative">
				<div class="relative">
					<button type="button" on:click={() => (showAdvisorList = !showAdvisorList)} class="relative w-full rounded-md bg-white py-1.5 pl-3 pr-10 text-left text-gray-900 shadow-sm ring-1 ring-gray-300 focus:outline-none sm:text-sm">
						<span class="flex items-center">
							<img src={avatars[selectedAdvisor]} alt="" class="w-6 h-6 mr-2 rounded-full" />
							<span class="block truncate mr-1">{selectedAdvisor}</span>
							<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="size-5">
								<path fill-rule="evenodd" d="M5.22 8.22a.75.75 0 0 1 1.06 0L10 11.94l3.72-3.72a.75.75 0 1 1 1.06 1.06l-4.25 4.25a.75.75 0 0 1-1.06 0L5.22 9.28a.75.75 0 0 1 0-1.06Z" clip-rule="evenodd" />
							</svg>
						</span>
					</button>

					{#if showAdvisorList}
						<ul class="dropdown-menu">
							{#each Object.keys(avatars).filter(advisor => advisor !== "User") as advisor}
								<li on:click={() => selectAdvisor(advisor)} class="cursor-pointer px-4 py-2 text-gray-900 hover:bg-indigo-600 hover:text-white">
									<div class="flex items-center">
										<img src={avatars[advisor]} alt="" class="w-6 h-6 mr-2 rounded-full" />
										<span>{advisor}</span>
									</div>
								</li>
							{/each}
						</ul>
					{/if}
				</div>
			</div>

			<div class="grid grid-cols-2 gap-2">
				{#each prompts as prompt}
					<button
						on:click={() => sendMessage(prompt)}
						class="prompt-button w-full inline-flex items-center justify-center rounded-md px-3 py-2 text-sm font-semibold shadow-sm {isWaitingForResponse ? 'waiting' : ''}"
					>
						{prompt}
					</button>
				{/each}
			</div>
		</div>
	</div>

</div>
