import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { CreateIssueRequest, IssueDto, Page, ProjectDto, CommentDto } from './models/models';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

    constructor(private httpClient: HttpClient) {}

  listProjects(page = 0, size = 50) {
    const params = new HttpParams()
      .set('page', page)
      .set('size', size);
    return this.httpClient.get<Page<ProjectDto>>('/api/v1/projects', { params });
  }

   listIssues(
    projectId: number,
    params: { status?: string; priority?: string; page?: number; size?: number } = {}
  ) {
    let httpParams = new HttpParams()
      .set('page', String(params.page ?? 0))
      .set('size', String(params.size ?? 10))
      .set('sort', 'updatedAt,desc');

    if (params.status)   httpParams = httpParams.set('status', params.status);
    if (params.priority) httpParams = httpParams.set('priority', params.priority);

    return this.httpClient.get<Page<IssueDto>>(
      `/api/v1/projects/${projectId}/issues`,
      { params: httpParams }
    );
  }

    getIssue(id: number) {
    return this.httpClient.get<IssueDto>(`/api/v1/issues/${id}`);
  }

  createIssue(projectId: number, body: CreateIssueRequest) {
    return this.httpClient.post<IssueDto>(`/api/v1/projects/${projectId}/issues`, body);
  }

  // Comments
  listComments(issueId: number) {
    return this.httpClient.get<CommentDto[]>(`/api/v1/issues/${issueId}/comments`);
  }

  addComment(issueId: number, body: string) {
    return this.httpClient.post(`/api/v1/issues/${issueId}/comments`, { body });
  }
}
