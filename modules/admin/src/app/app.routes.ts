import { provideRouter, RouterConfig } from "@angular/router";
import { AuthGuard } from "./common/authGuard";
import { Login } from "./login/login.component";
import { Navigation } from "./navigation/navigation.component";
import { Dashboard } from "./dashboard/dashboard.component";
import { Customers } from "./customers/customers.components";
import { Crud } from "./crud/crud.component";
import { NotFound } from "./notFound/notFound.component";
import { CrudView } from "./crud/crudView/crud.view";
import { CrudDelete } from "./crud/crudDelete/crud.delete";
import { CrudCreate } from "./crud/crudCreate/crud.create";
import {CrudEdit} from "./crud/crudEdit/crud.edit";
import {ResolveData} from "./common/resolveData";

const routes:RouterConfig = [
    {
        path: 'login',
        component: Login
    },
    {
        path: '',
        component: Navigation,
        canActivate: [AuthGuard],
        children: [
            {
                path: '',
                component: Dashboard,
                data: {
                    showInSubNavigation: true,
                    icon: 'layers'
                }
            },
            {
                path: 'customers',
                component: Customers,
                data: {
                    showInSubNavigation: true,
                    paramsAsDefault: '',
                    icon: 'perm_contact_calendar',
                    crudClass: 'Customer'
                },
                children: [
                    {
                        path: '',
                        component: Crud,
                        children: [
                            { path: '',  component: CrudView },
                            { path: 'delete/:id',  component: CrudDelete, resolve: [ResolveData] },
                            { path: 'create/:id',  component: CrudCreate, resolve: [ResolveData] },
                            { path: 'edit/:id',  component: CrudEdit, resolve: [ResolveData] },
                        ]
                    }
                ]
            }
        ]
    },
    {
        path: '',
        component: NotFound
    }
];

export const appRouterProviders = [
    provideRouter(routes)
];
