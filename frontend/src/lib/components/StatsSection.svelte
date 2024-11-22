<script>
	export let transactions = [];

	const calculateMonthlySpending = () =>
		transactions.reduce((sum, transaction) => sum + transaction.amount, 0);
</script>

<div class="mt-6 grid grid-cols-1 sm:grid-cols-3 gap-4">
	<div class="bg-white rounded-lg shadow p-5">
		<h3 class="text-sm text-gray-500">Monthly Spending</h3>
		<p class="text-2xl font-semibold text-gray-900">${calculateMonthlySpending()}</p>
	</div>
</div>
