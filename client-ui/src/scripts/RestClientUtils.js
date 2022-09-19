
export async function Get(url = '', getObject = {}) {
    let response = {};
    const requestOptions = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(getObject)
    };
    await fetch(url, requestOptions)
        .then(response => response.json())
        .then(result => {
            response = result;
        });
    return JSON.parse(JSON.stringify(response));
}

export async function Post(url = '', postObject = {}) {
    let response = {};
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(postObject)
    };
    await fetch(url, requestOptions)
        .then(response => response.json())
        .then(result => {
            response = result;
        });
    return JSON.parse(JSON.stringify(response));
}