<script>
	export let searchQuery = '';
</script>

<div class="mt-4">
	<input
		type="text"
		bind:value={searchQuery}
		placeholder="Search transactions"
		class="block w-full rounded-md border border-gray-300 py-2 px-3 shadow-sm focus:border-indigo-500 focus:ring-indigo-500"
	/>
</div>
