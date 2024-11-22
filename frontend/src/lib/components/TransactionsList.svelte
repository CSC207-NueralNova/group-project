<script>
	export let transactions = [];
</script>

<div class="mt-8">
	{#if transactions.length === 0}
		<p class="text-gray-600">No transactions found.</p>
	{:else}
		<div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
			{#each transactions as { id, description, amount, date }}
				<div class="bg-white rounded-lg shadow p-5">
					<h3 class="text-sm text-gray-500">{description}</h3>
					<p class="text-lg font-semibold text-gray-900">${amount}</p>
					<p class="text-sm text-gray-500">{date}</p>
				</div>
			{/each}
		</div>
	{/if}
</div>
