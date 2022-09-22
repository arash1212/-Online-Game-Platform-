import RestClient from "../../scripts/RestClient"

const loginJwt = (data) => {
   return RestClient.post('/pub/auth/jwt', data);
}

export {loginJwt};