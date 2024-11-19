import { signInWithEmailAndPassword, createUserWithEmailAndPassword } from "firebase/auth";
import { auth } from "./firebase";

/**
 * Sign up a user with email and password.
 * @param {string} email
 * @param {string} password
 * @returns {Promise<Object>} - User credentials or error.
 */
export const signUp = async (email, password) => {
	try {
		const userCredential = await createUserWithEmailAndPassword(auth, email, password);
		return { user: userCredential.user, error: null };
	} catch (error) {
		return { user: null, error: error.message };
	}
};

/**
 * Sign in a user with email and password.
 * @param {string} email
 * @param {string} password
 * @returns {Promise<Object>} - User credentials or error.
 */
export const signIn = async (email, password) => {
	try {
		const userCredential = await signInWithEmailAndPassword(auth, email, password);
		return { user: userCredential.user, error: null };
	} catch (error) {
		return { user: null, error: error.message };
	}
};
