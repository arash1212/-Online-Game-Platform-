import RestClient from "../../scripts/RestClient"

const findAll = () => {
   return RestClient.get('/pub/users');
}

const create = (data) => {
    return RestClient.post('/pub/users', data);
}

export default { findAll, create };