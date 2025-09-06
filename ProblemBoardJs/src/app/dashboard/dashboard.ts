import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../api-service';
import { CommentDto, IssueDto, ProjectDto } from '../models/models';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule, FormsModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss'
})
export class Dashboard {
  projects = signal<ProjectDto[]>([]);
  projectId = signal<number | undefined>(undefined);
  projectName = signal<string | undefined>(undefined);
  issues = signal<IssueDto[]>([]);
  page = signal(0);
  size = 10;
  totalPages = signal<number | undefined>(undefined);
  currentIssue = signal<IssueDto | undefined>(undefined);
  comments = signal<CommentDto[]>([]);


  fStatus = '';
  fPriority = '';
  form = { title: '', description: '', priority: 'MEDIUM', assigneeId: undefined as number | undefined };
  commentText = '';


  loaded = signal<boolean>(false);


  constructor(private api: ApiService) { }


  ngOnInit() {
    this.loadProjects();
  }


  loadProjects() {
    this.api.listProjects().subscribe({
      next: (p) => {
        const list = (p as any).content ?? (p as any);
        this.projects.set(list);
        if (list.length) {
          this.selectProject(list[0]);
        }
        this.loaded.set(true);
      },
      error: (_) => {
        this.loaded.set(true);
      },
    });
  }


  selectProject(p: ProjectDto) {
    this.projectId.set(p.id);
    this.projectName.set(p.name);
    this.page.set(0);
    this.reloadIssues();
    this.currentIssue.set(undefined);
  }


  reloadIssues() {
    if (!this.projectId()) return;
    this.api
      .listIssues(this.projectId()!, {
        status: this.fStatus || undefined,
        priority: this.fPriority || undefined,
        page: this.page(),
        size: this.size,
      })
      .subscribe((pg) => {
        this.issues.set(pg.content);
        this.totalPages.set(pg.totalPages);
      });
  }


  prev() {
    if (this.page() > 0) {
      this.page.set(this.page() - 1);
      this.reloadIssues();
    }
  }


  next() {
    if (this.page() + 1 < (this.totalPages() || 1)) {
      this.page.set(this.page() + 1);
      this.reloadIssues();
    }
  }


  openIssue(i: IssueDto) {
    this.api.getIssue(i.id).subscribe((x) => {
      this.currentIssue.set(x);
      this.loadComments(x.id);
    });
  }


  loadComments(id: number) {
    this.api.listComments(id).subscribe((list) => this.comments.set(list));
  }


  addComment() {
    if (!this.currentIssue() || !this.commentText.trim()) return;
    this.api.addComment(this.currentIssue()!.id, this.commentText.trim()).subscribe((_) => {
      this.commentText = '';
      this.loadComments(this.currentIssue()!.id);
    });
  }


  createIssue() {
    if (!this.projectId()) return;
    const body = {
      title: this.form.title.trim(),
      description: this.form.description?.trim(),
      priority: this.form.priority as any,
      assigneeId: this.form.assigneeId || undefined,
    };
    this.api.createIssue(this.projectId()!, body).subscribe((_) => {
      // close modal
      (document.querySelector('#issueModal .btn-close') as HTMLButtonElement)?.click();
      this.form = { title: '', description: '', priority: 'MEDIUM', assigneeId: undefined };
      this.reloadIssues();
    });
  }


  statusClass(s: string) {
    return {
      'text-bg-success': s === 'RESOLVED',
      'text-bg-warning': s === 'IN_PROGRESS',
      'text-bg-secondary': s === 'OPEN',
      badge: true,
    } as const;
  }
}
