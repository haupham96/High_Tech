import {Observable} from "knockout";
import * as path from "path";

var $: any = require('jquery');

export class PersonService {
    path = {
        getData: 'https://jsonplaceholder.typicode.com/posts'
    }

    constructor() {

    }

    getData():Promise<any> {
        return $.getJSON(this.path.getData);
    }
}

export interface Post {
    userId?: number,
    id?: number,
    title?: string,
    body?: string
}