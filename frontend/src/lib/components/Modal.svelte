<script>
	import { onMount } from 'svelte';

	export let title = '';
	export let isOpen = false;
	export let onClose = () => {};
	export let onSave = (data) => {};
	export let defaultData = { date: '', category: '', value: '', isRecurrent: false };
	export let categories = []; // Array of category options

	let formData = { ...defaultData };

	// Set default date to today if not provided
	onMount(() => {
		console.log('Initial formData.date:', formData.date);
		if (!formData.date) {
			const today = new Date();
			formData.date = `${today.getFullYear()}-${String(today.getMonth() + 1).padStart(2, '0')}-${String(today.getDate()).padStart(2, '0')}`;
		}
		console.log('Defaulted formData.date:', formData.date);
	});

	// Format the date to MMYY
	const formatDateToMMYY = (date) => {
		const [year, month] = date.split('-');
		return `${month}${year.slice(2)}`;
	};

	const closeModal = () => {
		onClose();
	};

	const handleSave = () => {
		// Create a copy of formData for submission
		const submissionData = { ...formData };

		// Convert the date to MMYY format
		if (submissionData.date) {
			submissionData.date = formatDateToMMYY(submissionData.date);
		}

		// Pass the processed data to onSave
		onSave(submissionData);
		closeModal();
	};

	const toggleRecurrent = () => {
		formData.isRecurrent = !formData.isRecurrent;
	};
</script>

{#if isOpen}
	<div class="fixed inset-0 z-50 flex items-center justify-center bg-gray-900 bg-opacity-50">
		<div class="bg-white w-full max-w-lg rounded-lg shadow-lg p-6">
			<div class="flex justify-between items-center">
				<h3 class="text-lg font-semibold text-gray-900">{title}</h3>
				<button
						class="text-gray-500 hover:text-gray-700"
						on:click={closeModal}
				>
					&times;
				</button>
			</div>
			<div class="mt-4 space-y-4">
				<div>
					<label class="block text-sm font-medium text-gray-700">Date</label>
					<input
							type="date"
							bind:value={formData.date}
							class="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
					/>
				</div>
				{#if title == "Add Expense"}
				<div>
					<label class="block text-sm font-medium text-gray-700">Category</label>
					<select
							bind:value={formData.category}
							class="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
					>
						<option value="" disabled>Select a category</option>
						{#each categories as category}
							<option value={category}>{category}</option>
						{/each}
					</select>
				</div>
				{/if}
				<div>
					<label class="block text-sm font-medium text-gray-700">Amount</label>
					<input
							type="number"
							bind:value={formData.value}
							on:input={() => (formData.value = parseFloat(formData.value || 0))}
							class="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
							placeholder="Enter amount"
					/>
				</div>
				<div class="flex items-center justify-end space-x-4">
					<label for="isRecurrent" class="text-sm font-medium text-gray-700">
						This is a recurrent amount
					</label>
					<div class="relative">
						<!-- Checkbox -->
						<div class="group grid size-4 grid-cols-1">
							<input
									id="isRecurrent"
									name="isRecurrent"
									type="checkbox"
									bind:checked={formData.isRecurrent}
									class="col-start-1 row-start-1 appearance-none rounded border border-gray-300 bg-white checked:border-indigo-600 checked:bg-indigo-600 indeterminate:border-indigo-600 indeterminate:bg-indigo-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 disabled:border-gray-300 disabled:bg-gray-100 disabled:checked:bg-gray-100 forced-colors:appearance-auto"
							/>
							<svg
									class="pointer-events-none col-start-1 row-start-1 size-3.5 self-center justify-self-center stroke-white group-has-[:disabled]:stroke-gray-950/25"
									viewBox="0 0 14 14"
									fill="none"
							>
								<path
										class="opacity-0 group-has-[:checked]:opacity-100"
										d="M3 8L6 11L11 3.5"
										stroke-width="2"
										stroke-linecap="round"
										stroke-linejoin="round"
								/>
								<path
										class="opacity-0 group-has-[:indeterminate]:opacity-100"
										d="M3 7H11"
										stroke-width="2"
										stroke-linecap="round"
										stroke-linejoin="round"
								/>
							</svg>
						</div>
					</div>
				</div>

			</div>
			<div class="mt-4 flex justify-end space-x-2">
				<button
						type="button"
						class="px-4 py-2 bg-gray-200 rounded-md hover:bg-gray-300"
						on:click={closeModal}
				>
					Cancel
				</button>
				<button
						type="button"
						class="px-4 py-2 bg-indigo-500 text-white rounded-md hover:bg-indigo-600"
						on:click={handleSave}
				>
					Save
				</button>
			</div>
		</div>
	</div>
{/if}
