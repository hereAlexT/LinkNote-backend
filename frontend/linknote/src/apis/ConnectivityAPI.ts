import { API_HEALTH } from "./ApiConfig";
import axios from 'axios'




async function GetServerHealthData() {
    try {
        const response = await axios.get(API_HEALTH);
        return response.data;
    } catch (error) {
        console.error('Error fetching health data:', error);
        throw error;
    }
}

export { GetServerHealthData };
