import Person from '../model/person';
// @ts-ignore
import template from './person-edit.html';

class PersonEdit {
    person: Person;

    constructor(params: { person: Person }) {
        this.person = params.person;
    }
}

export default {
    viewModel:PersonEdit,
    template:template
};
