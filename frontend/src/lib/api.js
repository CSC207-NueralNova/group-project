export async function chatbotRequest(username, message) {
	const response = await fetch(`${import.meta.env.VITE_API_URL}/chatbot/chat`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({ username, message })  // Sending username and message in the body
	});

	if (!response.ok) {
		throw new Error('Failed to get response from the chatbot');
	}

	return await response.json();
}
