<script>
	// Import any necessary logic or data here
	let category;
</script>

<div class="bg-white">
	<div class="relative isolate overflow-hidden bg-gradient-to-b from-[#4784B8]/20">
		<div class="mx-auto max-w-7xl pb-24 pt-10 sm:pb-32 lg:grid lg:grid-cols-2 lg:gap-x-8 lg:px-8 lg:py-40">
			<!-- Left Section: Headline and CTA -->
			<div class="px-6 lg:px-0 lg:pt-4">
				<div class="mx-auto max-w-2xl">
					<div class="max-w-lg">
						<div class="flex items-center space-x-3">
							<img class="h-11" src="/logo.svg" alt="SpendSmart Logo" />
							<span class="text-xl font-semibold text-gray-900">SpendSmart</span>
						</div>
						<div class="mt-8 sm:mt-8 lg:mt-8">
							<a href="/advisor" class="inline-flex space-x-6">
                <span
									class="rounded-full bg-[#6891B6]/10 px-3 py-1 text-sm font-semibold text-[#4784B8] ring-1 ring-inset ring-[#6891B6]/10"
								>What's new</span
								>
								<span class="inline-flex items-center space-x-2 text-sm font-medium text-gray-600">
                  <span>Introducing AI Budgeting Assistant</span>
                  <svg
										class="size-5 text-gray-400"
										viewBox="0 0 20 20"
										fill="currentColor"
										aria-hidden="true"
										data-slot="icon"
									>
                    <path
											fill-rule="evenodd"
											d="M8.22 5.22a.75.75 0 0 1 1.06 0l4.25 4.25a.75.75 0 0 1 0 1.06l-4.25 4.25a.75.75 0 0 1-1.06-1.06L11.94 10 8.22 6.28a.75.75 0 0 1 0-1.06Z"
											clip-rule="evenodd"
										/>
                  </svg>
                </span>
							</a>
						</div>
						<h1 class="mt-10 text-5xl font-semibold tracking-tight text-gray-900 sm:text-7xl">
							Master Your Money with <span class="text-[#4784B8]">SpendSmart</span>
						</h1>
						<p class="mt-8 text-lg font-medium text-gray-500 sm:text-xl">
							Track expenses, set budgets, and get AI-powered insights to stay on top of your finances.
						</p>
						<div class="mt-10 flex items-center gap-x-6">
							<a
								href="/signup"
								class="rounded-md bg-[#4784B8] px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-[#4784B8] focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-[#6891B6]"
							>Get Started</a
							>
							<div class="flex items-center space-x-3">
								<img class="h-11" src="/github.svg" alt="SpendSmart Logo" />
								<a href="https://github.com/CSC207-NueralNova/group-project" class="text-sm font-semibold text-gray-900">CSC207 Course Project Repo <span aria-hidden="true">→</span></a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Right Section: Mockup or Visual -->
			<div class="mt-20 sm:mt-24 md:mx-auto md:max-w-2xl lg:mx-0 lg:mt-0 lg:w-screen">
				<div
					class="absolute inset-y-0 right-1/2 -z-10 -mr-10 w-[200%] skew-x-[-30deg] bg-white shadow-xl shadow-[#4784B8]/10 ring-1 ring-[#6891B6]/10 md:-mr-20 lg:-mr-36"
					aria-hidden="true"
				></div>
				<div class="shadow-lg md:rounded-3xl">
					<div class="bg-[#4784B8] [clip-path:inset(0)] md:[clip-path:inset(0_round_theme(borderRadius.3xl))]">
						<div
							class="absolute -inset-y-px left-1/2 -z-10 ml-10 w-[200%] skew-x-[-30deg] bg-[#4784B8]/20 opacity-20 ring-1 ring-inset ring-white md:ml-20 lg:ml-36"
							aria-hidden="true"
						></div>
						<div class="relative px-6 pt-8 sm:pt-16 md:pl-16 md:pr-0">
							<div class="mx-auto max-w-2xl md:mx-0 md:max-w-none">
								<div class="w-screen overflow-hidden rounded-tl-xl bg-gray-900">
									<div class="flex bg-gray-800/40 ring-1 ring-white/5">
										<div class="-mb-px flex text-sm font-medium text-gray-400">
											<div
												class="border-b border-r border-b-white/20 border-r-white/10 bg-white/5 px-4 py-2 text-white"
											>
												MonthlyBudget.js
											</div>
											<div class="border-r border-gray-600/10 px-4 py-2">ExpenseTracker.js</div>
										</div>
									</div>
									<div class="px-6 pb-0 pt-10 text-gray-300">
                    <pre><code>// AI-Powered Budgeting Assistant
const monthlyBudget = calculateBudget(income, expenses);
console.log("Your Suggested Savings: ", monthlyBudget.savings);

// Stay on track with SpendSmart
const spendingLimit = suggestSpendingLimit(monthlyBudget);
console.log("Weekly Limit: ", spendingLimit.weekly);

const categorizedTotals = groupExpensesByCategory(expenses);
console.log("Category Totals: ", categorizedTotals);

// Optimize Spending Patterns
const optimizedPlan = optimizeSpendingPattern(expenses);
console.log("Optimization Plan: ", optimizedPlan);

// Get started now
										</code></pre>
									</div>
								</div>
							</div>
							<div
								class="pointer-events-none absolute inset-0 ring-1 ring-inset ring-black/10 md:rounded-3xl"
								aria-hidden="true"
							></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div
			class="absolute inset-x-0 bottom-0 -z-10 h-24 bg-gradient-to-t from-white sm:h-32"
		></div>
	</div>
</div>
