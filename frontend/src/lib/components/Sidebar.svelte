<script>
	import { auth } from '$lib/firebase.js';

	const handleLogout = async () => {
		try {
			await auth.signOut();
			window.location.href = '/login';
		} catch (error) {
			console.error('Logout error:', error.message);
		}
	};
</script>

<div class="hidden lg:fixed lg:inset-y-0 lg:flex lg:w-64 lg:flex-col">
	<div class="flex grow flex-col overflow-y-auto bg-cyan-700 pb-4 pt-5">
		<div class="flex shrink-0 items-center px-4">
			<img class="h-8 w-auto" src="https://tailwindui.com/plus/img/logos/mark.svg?color=cyan&shade=300" alt="Logo">
		</div>
		<nav class="mt-5 flex flex-1 flex-col divide-y divide-cyan-800 overflow-y-auto" aria-label="Sidebar">
			<div class="space-y-1 px-2">
				<a href="#" class="group flex items-center rounded-md bg-cyan-800 px-2 py-2 text-sm font-medium text-white">
					<svg class="mr-4 h-6 w-6 text-cyan-200" fill="none" stroke="currentColor" viewBox="0 0 24 24">
						<path d="M2.25 12l8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12" />
					</svg>
					Home
				</a>
				<a href="#" class="group flex items-center rounded-md px-2 py-2 text-sm font-medium text-cyan-100 hover:bg-cyan-600 hover:text-white">
					<svg class="mr-4 h-6 w-6 text-cyan-200" fill="none" stroke="currentColor" viewBox="0 0 24 24">
						<path d="M12 6v6h4.5" />
					</svg>
					History
				</a>
				<button
					on:click={handleLogout}
					class="group flex items-center rounded-md px-2 py-2 text-sm font-medium text-cyan-100 hover:bg-cyan-600 hover:text-white"
				>
					<svg class="mr-4 h-6 w-6 text-cyan-200" fill="none" stroke="currentColor" viewBox="0 0 24 24">
						<path d="M6 18l12-12M6 6l12 12" />
					</svg>
					Logout
				</button>
			</div>
		</nav>
	</div>
</div>
