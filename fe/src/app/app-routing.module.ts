import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './main/main.component';
import { DashboardComponentComponent } from './dashboard-component/dashboard-component.component';

const routes: Routes = [{path:'',component:MainComponent},{path:'b',component:DashboardComponentComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
