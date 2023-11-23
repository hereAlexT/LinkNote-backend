


const BASE_API_URL_V1 = 'http://192.9.179.115:8080/api/v1';


export default interface HealthData {
    data: {
        health: boolean;
        version: string;
    };
    code: number;
    msg: string;
}
export const API_HEALTH = BASE_API_URL_V1 + '/health';