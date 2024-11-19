import { sveltekit } from '@sveltejs/kit/vite';
import { defineConfig } from 'vite';

export default defineConfig({
	plugins: [sveltekit()],
	server: {
		proxy: {
			'/auth': 'http://localhost:8080', // Redirects `/firebase` API calls to Spring Boot backend
		}
	}
});
