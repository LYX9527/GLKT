declare module '*.vue' {
    import type {DefineComponent} from 'vue'
    // eslint-disable-next-line @typescript-eslint/no-explicit-any, @typescript-eslint/ban-types
    const component: DefineComponent<{}, {}, any>
    export default component
}

declare module "js-cookie" {
    export default class Cookies {
        static set(key: string, value: string, options?: any): void;

        static get(key: string): string | undefined;

        static remove(key: string, options?: any): void;
    }
}
