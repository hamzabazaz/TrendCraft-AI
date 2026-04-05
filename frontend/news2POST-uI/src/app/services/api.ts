import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from '../models/article.model';
import { GenerateRequest } from '../models/generate-request.model';
import { GenerateResponse } from '../models/generate-response.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class Api {

  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  fetchArticles(topic: string): Observable<{ topic: string; articles: Article[] }> {
    const params = new HttpParams().set('topic', topic);
    return this.http.get<{ topic: string; articles: Article[] }>(
      `${this.baseUrl}/api/articles`,
      { params }
    );
  }

  generatePost(payload: GenerateRequest): Observable<GenerateResponse> {
    return this.http.post<GenerateResponse>(
      `${this.baseUrl}/api/generate`,
      payload
    );
  }
}