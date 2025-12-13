import { CommonModule } from '@angular/common';
import { Component, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { ArticleList } from '../article-list/article-list';
import { GenerateResult } from '../generate-result/generate-result';
import { Article } from '../../models/article.model';
import { GenerateResponse } from '../../models/generate-response.model';
import { Api } from '../../services/api';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ArticleList,
    GenerateResult
  ],
  templateUrl: './home.html',
  styleUrls: ['./home.scss']
})
export class Home {

  topic = '';
  articles: Article[] = [];
  result?: GenerateResponse;
  loading = false;

  constructor(
    private api: Api,
    private cdr: ChangeDetectorRef // 🔥 ADD THIS
  ) {}
   get selectedCount(): number {
    return this.articles.filter(a => a.selected).length;
  }

  // 🔥 GETTER 2 → Disable generate button if no items or > 3 selected
  get disableGenerate(): boolean {
    return this.selectedCount === 0 || this.selectedCount > 3;
  }

  fetchArticles() {
    if (!this.topic.trim()) return;

    this.loading = true;
    this.articles = [];
    this.cdr.detectChanges(); // 🔥 FORCE UPDATE

    this.api.fetchArticles(this.topic).subscribe({
      next: (res) => {
        console.log('API response:', res);

        this.articles = res.articles;
        this.loading = false;

        this.cdr.detectChanges(); // 🔥🔥 THIS FIXES IT
      },
      error: (err) => {
        console.error(err);
        this.loading = false;
        this.cdr.detectChanges();
      }
    });
    
  }
toggleSelect(article: Article) {
  const selectedCount = this.articles.filter(a => a.selected).length;

  // Prevent selecting more than 3
  if (!article.selected && selectedCount >= 3) {
    alert("You can only select a maximum of 3 articles.");
    return;
  }

  article.selected = !article.selected;
  this.articles = [...this.articles];
  this.cdr.detectChanges();
}


  

  generate() {
    const selected = this.articles.filter(a => a.selected);
    if (!selected.length) {
      alert('Select at least one article');
      return;
    }

    this.api.generatePost({
      topic: this.topic,
      articles: selected
    }).subscribe({
      next: res => {
        this.result = res;
        this.cdr.detectChanges();
      },
      error: () => alert('Generation failed')
    });
  }
}
