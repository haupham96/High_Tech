import * as ko from 'knockout';
import {
    Observable, ObservableArray
} from 'knockout';

class Person {
    firstName: Observable<string>;
    lastName: Observable<string>;
    favouriteFilms: ObservableArray<string>;
    fullName = ko.pureComputed(() => this.firstName() + ' ' + this.lastName());

    constructor(firstName: string, lastName: string, favouriteFilm: string[] | null) {
        this.firstName = ko.observable(firstName);
        this.lastName = ko.observable(lastName);
        this.favouriteFilms = ko.observableArray(favouriteFilm || []);
    }
}

export default Person;