export type IssueStatus = 'OPEN'|'IN_PROGRESS'|'RESOLVED';
export type IssuePriority = 'LOW'|'MEDIUM'|'HIGH';

export interface Page<T> { content:T[]; number:number; totalPages:number; first:boolean; last:boolean; }
export interface ProjectDto { id:number; name:string; description?:string; archived:boolean; createdAt:string; }
export interface IssueDto {
  id:number; projectId:number; title:string; description?:string;
  status:IssueStatus; priority:IssuePriority; assigneeId?:number|null;
  createdAt:string; updatedAt:string;
}
export interface CommentDto { id:number; authorId:number; body:string; createdAt:string; }
export interface CreateIssueRequest { title:string; description?:string; priority:IssuePriority; assigneeId?:number|null; }
export interface SoapSummary { openCount:number; inProgressCount:number; resolvedCount:number; lastUpdated?:string; }
