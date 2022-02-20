import Axios from "axios";

const url = Axios.create({
    baseURL: 'http://localhost:8080/',
});

const api = {
    get: (id) => {
        return url.get(`messages/${id}`);
    },

    post: (username, text) => {
        let message = {
            sender: username,
            content: text
        }
        return url.post('', message);
    }
}

export default api;