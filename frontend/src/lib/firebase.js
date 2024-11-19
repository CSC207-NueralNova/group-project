import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";

const firebaseConfig = {
	apiKey: "AIzaSyDSTl_pfsN7dW0CiS26OUwtfLQFUuj5nS0",
	authDomain: "spendsmart-ce9a9.firebaseapp.com",
	projectId: "spendsmart-ce9a9",
	storageBucket: "spendsmart-ce9a9.firebasestorage.app",
	messagingSenderId: "1088917026117",
	appId: "1:1088917026117:web:b4a907cf0f31056b85fa2c",
};

const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);