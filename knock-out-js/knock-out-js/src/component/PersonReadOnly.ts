import Person from '../model/person';
// @ts-ignore
import template from './person-read-only.html';

class PersonReadOnly {
    person: Person;

    constructor(params: { person: Person }) {
        this.person = params.person;
    }
}

export default {
    viewModel:PersonReadOnly,
    template:template
};
