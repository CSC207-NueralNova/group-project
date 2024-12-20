/**
 * Fetches user data from the backend `see_list` API for the specified months.
 *
 * @param {string} userId - The UID of the user whose data is to be fetched.
 * @param {Array<string>} dates - An array of months in MMYY format (e.g., ["1123", "1023", "0923"]).
 * @returns {Promise<Object>} - Resolves with the output data from the backend API.
 * @throws {Error} - Throws an error if fetching fails.
 */
export async function fetchUserData(userId, dates) {
    if (!userId) {
        throw new Error("User ID must not be empty");
    }
    if (!dates || dates.length === 0) {
        throw new Error("Dates array must not be empty");
    }

    try {
        console.log("Calling API with:", { userId, dates }); // Log input parameters
        const response = await fetch("http://localhost:8080/api/see_list/see", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ userId, dates }),
        });

        console.log("Response status:", response.status); // Log response status
        if (!response.ok) {
            throw new Error(`Failed to fetch user data: ${response.statusText}`);
        }

        const data = await response.json();
        console.log("Response JSON:", data); // Log the parsed JSON
        return data; // Return the JSON data
    } catch (error) {
        console.error("Error fetching user data:", error);
        throw new Error("Failed to fetch user data from the backend");
    }
}
