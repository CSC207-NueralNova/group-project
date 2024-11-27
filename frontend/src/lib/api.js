import { env } from '$env/dynamic/public'; // Dynamic public environment variables

export async function chatbotRequest(username, message) {
	console.log("Sending request to backend:", { username, message }); // Debugging log

	const apiUrl = env.PUBLIC_VITE_API_URL; // Use environment variable with PUBLIC_ prefix

	try {
		const response = await fetch(`${apiUrl}/api/chatbot/chat`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify({ username, message }), // Ensure this structure matches your backend
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
