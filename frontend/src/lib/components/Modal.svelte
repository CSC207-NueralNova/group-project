<script>
	import { createEventDispatcher } from 'svelte';

	export let isOpen = false;
	export let type = 'expense'; // Default to "expense"

	const dispatch = createEventDispatcher();

	function closeModal() {
		isOpen = false;
		dispatch('close');
	}

	function handleSubmit(event) {
		event.preventDefault();
		const formData = new FormData(event.target);
		const data = {
			...Object.fromEntries(formData.entries()),
			type, // Add the type to the form data
		};
		dispatch('submit', data);
		closeModal();
	}
</script>

{#if isOpen}
	<div class="fixed inset-0 z-50 bg-gray-500 bg-opacity-75 flex items-center justify-center">
		<div class="bg-white shadow-xl ring-1 ring-gray-900/5 sm:rounded-xl w-full max-w-md">
			<form on:submit|preventDefault={handleSubmit} class="p-6 space-y-6">
				<h2 class="text-xl font-semibold text-gray-900">
					Add {type === 'expense' ? 'Expense' : 'Income'}
				</h2>

				<!-- Amount -->
				<div class="space-y-1">
					<label for="amount" class="block text-sm font-medium text-gray-900">Amount</label>
					<input
						type="number"
						name="amount"
						id="amount"
						placeholder={`Enter ${type === 'expense' ? 'expense' : 'income'} amount`}
						required
						class="block w-full rounded-md border-0 bg-white py-1.5 pl-3 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm"
					/>
				</div>

				<!-- Category -->
				<div class="space-y-1">
					<label for="category" class="block text-sm font-medium text-gray-900">Category</label>
					<div class="relative">
						<input
							id="category"
							type="text"
							name="category"
							placeholder="Select a category"
							class="w-full rounded-md border-0 bg-white py-1.5 pl-3 pr-12 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm"
							role="combobox"
							aria-controls="category-options"
							aria-expanded="false"
						/>
						<button type="button" class="absolute inset-y-0 right-0 flex items-center rounded-r-md px-2 focus:outline-none">
							<svg class="h-5 w-5 text-gray-400" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor">
								<path fill-rule="evenodd" d="M10.53 3.47a.75.75 0 0 0-1.06 0L6.22 6.72a.75.75 0 0 0 1.06 1.06L10 5.06l2.72 2.72a.75.75 0 1 0 1.06-1.06l-3.25-3.25Zm-4.31 9.81 3.25 3.25a.75.75 0 0 0 1.06 0l3.25-3.25a.75.75 0 1 0-1.06-1.06L10 14.94l-2.72-2.72a.75.75 0 0 0-1.06 1.06Z" clip-rule="evenodd" />
							</svg>
						</button>
					</div>
					<ul class="absolute z-10 mt-1 max-h-60 w-full overflow-auto rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black/5 focus:outline-none sm:text-sm hidden" id="category-options" role="listbox">
						<li class="relative cursor-default select-none py-2 pl-3 pr-9 text-gray-900" role="option">
							Food
						</li>
						<li class="relative cursor-default select-none py-2 pl-3 pr-9 text-gray-900" role="option">
							Travel
						</li>
						<li class="relative cursor-default select-none py-2 pl-3 pr-9 text-gray-900" role="option">
							Shopping
						</li>
						<li class="relative cursor-default select-none py-2 pl-3 pr-9 text-gray-900" role="option">
							Utilities
						</li>
						<li class="relative cursor-default select-none py-2 pl-3 pr-9 text-gray-900" role="option">
							Other
						</li>
					</ul>
				</div>

				<!-- Description -->
				<div class="space-y-1">
					<label for="description" class="block text-sm font-medium text-gray-900">Description</label>
					<textarea
						id="description"
						name="description"
						rows="3"
						placeholder="Enter a brief description"
						required
						class="block w-full rounded-md border-0 bg-white py-1.5 pl-3 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm"
					></textarea>
				</div>

				<!-- Buttons -->
				<div class="flex items-center justify-end gap-x-4">
					<button
						type="button"
						on:click={closeModal}
						class="text-sm font-semibold text-gray-900"
					>
						Cancel
					</button>
					<button
						type="submit"
						class="rounded-md bg-indigo-600 px-4 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
					>
						Save
					</button>
				</div>
			</form>
		</div>
	</div>
{/if}

