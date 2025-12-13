import { Component, Input } from '@angular/core';
import { GenerateResponse } from '../../models/generate-response.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-generate-result',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './generate-result.html',
  styleUrl: './generate-result.scss',
})
export class GenerateResult {
  @Input() result!: GenerateResponse;
}
