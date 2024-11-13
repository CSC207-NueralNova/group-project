<script>
	import { chatbotRequest } from '$lib/api.js';
	import { onMount } from 'svelte';
	import Messages from '$lib/components/Messages.svelte';

	let username = '';
	let message = '';
	let responseMessage = '';

	async function sendMessage() {
		try {
			const response = await chatbotRequest(username, message);
			responseMessage = response.advice; // Adjust based on your API response structure
		} catch (error) {
			console.error(error);
			responseMessage = 'Error: Unable to reach the chatbot.';
		}
	}
</script>

<!--<h1>Chat with our AI Bot</h1>-->
<!--<input type="text" bind:value={username} placeholder="Enter your username" />-->
<!--<textarea bind:value={message} placeholder="Enter your message"></textarea>-->
<!--<button on:click={sendMessage}>Send</button>-->

<!--<p>{responseMessage}</p>-->
