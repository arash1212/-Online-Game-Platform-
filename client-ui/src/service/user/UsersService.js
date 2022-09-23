import RestClient from "../../scripts/RestClient"

const findAll = () => {
    return RestClient.get('/pub/users');
}

const find = (id) => {
    return RestClient.get(`/pub/users/${id}`);
}

const create = (data) => {
    return RestClient.post('/pub/users', data);
}

const remove = (id) => {
    return RestClient.delete(`/pub/users/${id}`);
}

export { findAll, create, remove, find };