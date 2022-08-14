import * as ko from 'knockout';
import {BindingHandler, ObservableArray} from 'knockout';

const filmBiding = {
    init(element: HTMLElement, valueAccestor: () => ObservableArray): void {
        let unwrappedValue = ko.unwrap(valueAccestor());
        let isPopulated = Array.isArray(unwrappedValue) && unwrappedValue.length > 0;
        let text = isPopulated ? unwrappedValue.join(", ") : "unknow";
        element.textContent = text ;
    }
} as BindingHandler;

export default filmBiding;