import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Article } from '../../models/article.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-article-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './article-list.html',
  styleUrls: ['./article-list.scss'],
})
export class ArticleList {
   @Input() articles: Article[] = [];
     @Output() toggle = new EventEmitter<Article>();

   onToggle(article: Article) {
    this.toggle.emit(article);
  }

}
