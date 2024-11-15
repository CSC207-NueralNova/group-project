import * as process from '../../.svelte-kit/ambient.js';

export async function chatbotRequest(username, message) {
	console.log("Sending request to backend:", { username, message }); // Debugging log

	try {
		const response = await fetch(`${process.env.APP_API_URL}/api/chatbot/chat`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({ username, message }) // Ensure this structure matches AskAIInputData
		});

		if (!response.ok) throw new Error('Failed to get response from chatbot');
		const data = await response.json();
		console.log("Received response from backend:", data);
		return data;
	} catch (error) {
		console.error("Error in chatbotRequest:", error);
		throw error;
	}
}
