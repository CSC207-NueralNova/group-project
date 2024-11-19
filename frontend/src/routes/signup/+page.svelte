<script>
	import { signUp } from "$lib/auth.js";
	import { goto } from "$app/navigation"

	let email = "";
	let password = "";
	let errorMessage = "";

	// Handle sign-up form submission
	const handleSignUp = async () => {
		errorMessage = ""; // Clear any previous error message
		try {
			const { user, error } = await signUp(email, password);
			if (error) {
				errorMessage = error; // Set error message to display
				console.error("Sign-up error:", error);
			} else {
				console.log("Account created successfully:", user);
				await goto('/dashboard')
			}
		} catch (err) {
			errorMessage = err.message; // Catch unexpected errors
			console.error("Unexpected error:", err);
		}
	};
</script>

<div class="flex min-h-screen flex-col justify-center px-6 py-12 lg:px-8">
	<div class="sm:mx-auto sm:w-full sm:max-w-sm">
		<img class="mx-auto h-10 w-auto" src="/logo.svg" alt="SpendSmart Logo" />
		<h2 class="mt-10 text-center text-2xl font-bold tracking-tight text-gray-900">Create your account</h2>
	</div>

	<div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
		<form class="space-y-6" on:submit|preventDefault={handleSignUp}>
			<div>
				<label for="email" class="block text-sm font-medium text-gray-900">Email address</label>
				<div class="mt-2">
					<input
						id="email"
						name="email"
						type="email"
						autocomplete="email"
						bind:value={email}
					required
					class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder-gray-400 focus:ring-2 focus:ring-inset focus:ring-[#6891B6] sm:text-sm"
					/>
				</div>
			</div>

			<div>
				<label for="password" class="block text-sm font-medium text-gray-900">Password</label>
				<div class="mt-2">
					<input
						id="password"
						name="password"
						type="password"
						autocomplete="new-password"
						bind:value={password}
					required
					class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder-gray-400 focus:ring-2 focus:ring-inset focus:ring-[#6891B6] sm:text-sm"
					/>
				</div>
			</div>

			<!-- Display error message if it exists -->
			{#if errorMessage}
				<p class="text-sm text-red-500">{errorMessage}</p>
			{/if}

			<div>
				<button
					type="submit"
					class="flex w-full justify-center rounded-md bg-[#6891B6] px-3 py-1.5 text-sm font-semibold text-white shadow-sm hover:bg-[#577a98] focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-[#6891B6]"
				>
					Sign up
				</button>
			</div>
		</form>

		<p class="mt-10 text-center text-sm text-gray-500">
			Already have an account?
			<a href="/login" class="font-semibold text-[#6891B6] hover:text-[#577a98]">Sign in</a>
		</p>
	</div>
</div>
