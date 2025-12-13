import { Article } from './article.model';

export interface GenerateRequest {
  topic: string;
  articles: Article[];
}
