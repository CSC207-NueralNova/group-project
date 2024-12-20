import { env } from '$env/dynamic/public'; // Dynamic public environment variables

const apiUrl = env.PUBLIC_VITE_API_URL; // Use environment variable with PUBLIC_ prefix

export async function chatbotRequest(username, message) {
	console.log("Sending request to backend:", { username, message }); // Debugging log
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

export async function saveIncomeToBackend(income) {
	try {
		const response = await fetch(`${apiUrl}/api/enter_income/enter`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(income),
		});

		if (!response.ok) {
			throw new Error(`Failed to save income: ${response.statusText}`);
		}

		const data = await response.json();
		return data;
	} catch (error) {
		console.error('Error saving income:', error);
		throw error;
	}
}

export async function saveRecurrentIncomeToBackend(income) {
	try {
		const response = await fetch(`${apiUrl}/api/enter_recurrent_income/enter`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(income),
		});

		if (!response.ok) {
			throw new Error(`Failed to save recurrent income: ${response.statusText}`);
		}

		const data = await response.json();
		return data;
	} catch (error) {
		console.error('Error saving income:', error);
		throw error;
	}
}

export async function saveExpenseToBackend(expense) {
	try {
		console.log('Payload being sent to backend:', expense); // Log payload
		const response = await fetch(`${apiUrl}/api/enter_expense/enter`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(expense),
		});

		if (!response.ok) {
			throw new Error(`Failed to save expense: ${response.statusText}`);
		}

		const data = await response.json();
		console.log('Response from backend:', data); // Log backend response
		return data;
	} catch (error) {
		console.error('Error saving expense:', error);
		throw error;
	}
}

export async function saveRecurrentExpenseToBackend(expense) {
	try {
		console.log('Payload being sent to backend:', expense); // Log payload
		const response = await fetch(`${apiUrl}/api/enter_recurrent_expense/enter`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(expense),
		});

		if (!response.ok) {
			throw new Error(`Failed to save recurrent expense: ${response.statusText}`);
		}

		const data = await response.json();
		console.log('Response from backend:', data); // Log backend response
		return data;
	} catch (error) {
		console.error('Error saving expense:', error);
		throw error;
	}
}
