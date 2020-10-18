import RestDataSource from "./restDataSource";

export const serviceCall = {
    fetchList,
    addRecord,
    editRecord,
    deleteRecord
};

function fetchList(fn) {

    var url = "http://localhost:8010/sms-group/fetchCities";

    return new RestDataSource(url, null, fn).GetData((res) => fn(res));

}

function addRecord(payload, fn) {

    var url = "http://localhost:8010/sms-group/add";

    return new RestDataSource(url, null, fn).Store(payload, (res) => fn(res));

}

function editRecord(payload, fn) {

    var url = "http://localhost:8010/sms-group/update";

    return new RestDataSource(url, null, fn).Update(payload, (res) => fn(res));

}

function deleteRecord(id, fn) {

    var url = "http://localhost:8010/sms-group/delete";

    return new RestDataSource(url, null, fn).Delete(id, (res) => fn(res));

}

