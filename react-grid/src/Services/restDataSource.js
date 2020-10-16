import Axios from "axios";

export default class RestDataSource {
    constructor(base_url, userId, errorCallback) {

        this.BASE_URL = base_url;
        this.handleError = errorCallback;
    }

    async GetData(callback) {
        this.SendRequest("get", this.BASE_URL, callback);
    }
    async GetOneByParam(id, callback) {
        this.SendRequest("get", `${this.BASE_URL}?${id}`, callback);
    }
    async GetOne(data, callback) {
        this.SendRequest("get", this.BASE_URL, callback, data);
    }
    async Store(data, callback) {

        this.SendRequest("post", this.BASE_URL, callback, data);
    }
    async Update(data, callback) {
        this.SendRequest("put", this.BASE_URL, callback, data);
    }
    async Delete(data, callback) {

        this.SendRequest("delete", this.BASE_URL, callback, data);
    }
    async SendRequest(method, url, callback, data) {
        try {
            let response = await Axios.request({

                method: method,
                url: url,
                data: data,
            });
            callback(response);
        } catch (err) {
            console.log(err);

        }
    }
}
