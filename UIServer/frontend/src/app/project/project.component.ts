import {Component, Injectable, OnInit} from '@angular/core';
import {ProjectService} from './project.service';
import {Project} from './project';
import { uniqueNamesGenerator, Config, adjectives, colors, animals } from 'unique-names-generator';
import {SourceFile} from '../editor/sourcefile';
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  providers: [ ProjectService ],
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {
  projects: Project[];
  modifyingProject: Project;
  modifying: boolean;
  currentUsername: string;
  usernames: string[];

  constructor(private projectService: ProjectService) { }

  ngOnInit(): void {
    this.getProjects();
  }

  // tslint:disable-next-line:typedef
  getProjects() {
    this.projectService.getProjects()
      .subscribe(projects => this.projects = projects);
  }

  initiateProjectUsername(username: string) {
    this.currentUsername = username;
    this.usernames = this.usernames = new Array<string>();
    this.usernames.push(username);
  }

  // tslint:disable-next-line:typedef
  createProject() {
    this.modifying = false;
    const randomName: string = uniqueNamesGenerator({
      dictionaries: [adjectives, colors, animals]
    });

    // const newSourceFileSet = new Set<SourceFile>();
    const newSourceFileSet = new Array<SourceFile>();

    this.projectService.getCurrentUsername().subscribe(currentUsername => this.initiateProjectUsername(currentUsername));
    console.log('usernames length is ' + this.usernames.length);
    console.log('current username is ' + this.currentUsername);

    // @ts-ignore
    const newProject: Project = { name: randomName, sourcefiles: newSourceFileSet, usernames: this.usernames } as Project;
    console.log('newProject.name = ' + newProject.name);
    console.log('newProject.sourcefiles = ' + newProject.sourcefiles);
    this.projectService.createProject(newProject).subscribe(project => this.projects.push(project));
  }

  // tslint:disable-next-line:typedef
  modifyProject(project: Project) {
    this.modifying = true;
    this.modifyingProject = project;
  }

  // tslint:disable-next-line:typedef
  updateProject() {
    if (this.modifying) {
      this.projectService.updateProject(this.modifyingProject).subscribe(updatedProject => {
        const index = this.projects.findIndex(project => project.id === this.modifyingProject.id);
        this.projects[index] = updatedProject;
      });
      this.modifying = false;
    }

  }

  deleteProject(deleteProject: Project): void {
    this.projects = this.projects.filter(project => project !== deleteProject);
    this.projectService.deleteProject(deleteProject.id).subscribe();
  }

  /*
  createProject(): void {
    console.log('This should create a Project');
    const project = new Project();
    project.users = 1;
    project.name = 'New Project';
    project.files = [];
    if (this.projects.length > 0) {
      project.id = this.projects[this.projects.length - 1].id + 1;
    }
    else {
      project.id = 0;
    }
    this.projects.push(project);
  }
  */
}