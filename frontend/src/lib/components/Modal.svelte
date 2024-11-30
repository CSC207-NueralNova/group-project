<script>
	import { onMount } from 'svelte';

	export let title = '';
	export let isOpen = false;
	export let onClose = () => {};
	export let onSave = (data) => {};
	export let defaultData = { date: '', category: '', value: '' };
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
