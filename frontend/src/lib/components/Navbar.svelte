<script>
	import { auth } from '$lib/firebase.js';

	const handleLogout = async () => {
		try {
			await auth.signOut(); // Log out user
			window.location.href = "/login"; // Redirect to login
		} catch (error) {
			console.error("Logout error:", error.message);
		}
	};
</script>
<header class="absolute inset-x-0 top-0 z-50 flex h-16 border-b border-gray-900/10">
	<div class="mx-auto flex w-full max-w-7xl items-center justify-between px-4 sm:px-6 lg:px-8">
		<div class="flex flex-1 items-center gap-x-6">
			<button type="button" class="-m-3 p-3 md:hidden">
				<span class="sr-only">Open main menu</span>
				<svg class="h-5 w-5 text-gray-900" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
					<path fill-rule="evenodd" d="M2 4.75A.75.75 0 0 1 2.75 4h14.5a.75.75 0 0 1 0 1.5H2.75A.75.75 0 0 1 2 4.75ZM2 10a.75.75 0 0 1 .75-.75h14.5a.75.75 0 0 1 0 1.5H2.75A.75.75 0 0 1 2 10Zm0 5.25a.75.75 0 0 1 .75-.75h14.5a.75.75 0 0 1 0 1.5H2.75a.75.75 0 0 1-.75-.75Z" clip-rule="evenodd" />
				</svg>
			</button>
			<div class="flex items-center space-x-3">
				<img class="h-8" src="/logo.svg" alt="SpendSmart Logo" />
				<span class="text-xl font-semibold text-gray-900">SpendSmart</span>
			</div>
		</div>
		<nav class="hidden md:flex md:gap-x-11 md:text-sm/6 md:font-semibold md:text-gray-700">
		</nav>
		<div class="flex flex-1 items-center justify-end gap-x-4">
			<a href="#" class="-m-1.5 p-1.5">
				<span class="sr-only">Your profile</span>
				<img
					class="h-8 w-8 rounded-full"
					src="/github.svg"
				>
			</a>
			<button
				type="button"
				class="rounded-md bg-red-500 px-3 py-1.5 text-sm font-semibold text-amber-50 shadow-sm hover:bg-red-400"
				on:click={handleLogout}
			>
				Logout
			</button>
		</div>
	</div>
</header>