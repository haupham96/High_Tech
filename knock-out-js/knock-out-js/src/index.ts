import * as ko from 'knockout';
import Person from "./model/person";
import PersonReadOnly from "./component/PersonReadOnly";
import PersonEdit from "./component/PersonEdit";
import filmBiding from "./biding/FilmBiding";
import {PersonService, Post} from "./service/person.service";
import {ObservableArray} from "knockout";

ko.components.register('person-view', PersonReadOnly);
ko.components.register('person-edit', PersonEdit);
ko.bindingHandlers.films = filmBiding;

class AppViewModel {
    person: Person
    posts: ObservableArray<Post> = ko.observableArray();
    personService: PersonService = new PersonService();

    constructor() {
        this.person = new Person(
            'Jon',
            'Keeping',
            ['The Matrix', 'The Shawshank Redemption', 'Upgrade']
        );

        this.personService.getData().then(async (data) => {
           this.posts(data);
            // console.log(this.posts());
        })
    }
}

ko.applyBindings(new AppViewModel(), document.getElementById('app'));

