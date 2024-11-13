<script>
	import { writable } from 'svelte/store';

	// Placeholder avatars and descriptions
	const avatars = {
		"Finance Expert": "/finance.png",
		"Stocks Expert": "/stocks.png",
		"Tax Advisor": "/tax.png",
		"User": "/pfp.jpg"
	};

	const advisorDescriptions = {
		"Finance Expert": "Your go-to expert for financial planning, investments, and managing wealth efficiently.",
		"Stocks Expert": "An expert in stock markets, providing insights on trading strategies and portfolio optimization.",
		"Tax Advisor": "Specialist in tax regulations, helping you with tax savings and compliance."
	};

	let messages = writable([]);
	let userInput = '';
	let selectedAdvisor = 'Finance Expert';
	let selectedCategory = 'Investment';
	let showAdvisorList = false;

	function sendMessage() {
		if (userInput.trim() === '') return;

		messages.update((msgs) => [
			...msgs,
			{
				sender: "You",
				avatar: avatars["User"],
				text: userInput,
				timestamp: new Date().toISOString(),
				relativeTime: "Just now",
				isAI: false
			}
		]);

		userInput = '';

		// Simulate AI response
		setTimeout(() => {
			messages.update((msgs) => [
				...msgs,
				{
					sender: selectedAdvisor,
					avatar: avatars[selectedAdvisor],
					text: `Here's some advice on ${selectedCategory} from our ${selectedAdvisor}.`,
					timestamp: new Date().toISOString(),
					relativeTime: "Just now",
					isAI: true
				}
			]);
		}, 1000);
	}
</script>

<div class="flex">
	<!-- Chat Box -->
	<div class="overflow-hidden rounded-lg border border-gray-300 shadow-sm flex-1">
		<ul role="list" class="divide-y divide-gray-100 max-h-80 overflow-y-auto bg-white px-4 py-2">
			{#each $messages as message (message.timestamp)}
				<li class="flex gap-x-4 py-3 {message.isAI ? 'text-left' : 'text-right'}">
					{#if message.isAI}
						<img class="w-12 h-12 flex-none rounded-full bg-gray-50" src={message.avatar} alt="AI Avatar">
						<div class="flex-auto">
							<div class="flex items-baseline justify-between gap-x-4">
								<p class="text-sm font-semibold text-gray-900">{message.sender}</p>
								<p class="flex-none text-xs text-gray-600">
									<time datetime={message.timestamp}>{message.relativeTime}</time>
								</p>
							</div>
							<p class="mt-1 text-sm text-gray-600">{message.text}</p>
						</div>
					{:else}
						<div class="flex-auto">
							<div class="flex items-baseline justify-between gap-x-4">
								<p class="text-sm font-semibold text-gray-900">{message.sender}</p>
								<p class="flex-none text-xs text-gray-600">
									<time datetime={message.timestamp}>{message.relativeTime}</time>
								</p>
							</div>
							<p class="mt-1 text-sm bg-indigo-500 text-white rounded-lg p-2">{message.text}</p>
						</div>
						<img class="w-12 h-12 flex-none rounded-full bg-gray-50" src={message.avatar} alt="User Avatar">
					{/if}
				</li>
			{/each}
		</ul>

		<div class="border-t border-gray-200 px-4 py-4">
			<div class="flex space-x-4 mb-3">
				<!-- Custom Select AI Advisor -->
				<div class="relative">
					<button type="button" on:click={() => (showAdvisorList = !showAdvisorList)} class="relative w-full rounded-md bg-white py-1.5 pl-3 pr-10 text-left text-gray-900 shadow-sm ring-1 ring-gray-300 focus:outline-none sm:text-sm">
						<span class="flex items-center">
							<img src={avatars[selectedAdvisor]} alt="" class="w-6 h-6 mr-2 rounded-full" />
							<span class="block truncate">{selectedAdvisor}</span>
						</span>
					</button>

					{#if showAdvisorList}
						<ul class="absolute z-10 mt-1 max-h-56 w-full overflow-auto rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black/5 focus:outline-none sm:text-sm">
							{#each Object.keys(avatars).filter(advisor => advisor !== "User") as advisor}
								<li on:click={() => { selectedAdvisor = advisor; showAdvisorList = false; }} class="cursor-pointer select-none py-2 pl-3 pr-9 text-gray-900 hover:bg-indigo-600 hover:text-white">
									<div class="flex items-center">
										<img src={avatars[advisor]} alt="" class="w-6 h-6 mr-2 rounded-full" />
										<span class="ml-3 block truncate">{advisor}</span>
									</div>
								</li>
							{/each}
						</ul>
					{/if}
				</div>

				<!-- Select Category -->
				<div class="relative">
					<label class="sr-only" for="category">Select Category</label>
					<select id="category" bind:value={selectedCategory} class="rounded-md border-gray-300 bg-gray-50 px-2 py-2 text-sm font-medium text-gray-500 hover:bg-gray-100 focus:border-indigo-500">
						<option value="Investment">Investment</option>
						<option value="Savings">Savings</option>
						<option value="Retirement">Retirement</option>
					</select>
				</div>
			</div>

			<!-- Message Entry Form -->
			<form on:submit|preventDefault={sendMessage} class="relative">
				<textarea
					bind:value={userInput}
					rows="1"
					class="block w-full resize-none border-0 py-2 text-gray-900 placeholder-gray-400 focus:ring-0 sm:text-sm"
					placeholder="Type a message..."
				></textarea>
				<div class="flex items-center justify-end mt-2">
					<button
						type="submit"
						class="inline-flex items-center rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500"
					>
						Send
					</button>
				</div>
			</form>
		</div>
	</div>

	<!-- Advisor Description Box -->
	<div class="ml-4 p-4 w-64 border border-gray-300 rounded-lg shadow-sm bg-white">
		<div class="flex items-center mb-2">
			<img src={avatars[selectedAdvisor]} alt="" class="w-10 h-10 mr-3 rounded-full" />
			<h4 class="text-lg font-bold text-gray-900">{selectedAdvisor}</h4>
		</div>
		<p class="text-sm text-gray-600">{advisorDescriptions[selectedAdvisor]}</p>
	</div>
</div>
