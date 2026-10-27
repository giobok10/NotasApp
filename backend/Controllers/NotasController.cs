using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Security.Claims;
using NotasApi.Models;

[Authorize]
[Route("api/[controller]")]
public class NotasController : ControllerBase
{
    private readonly AppDbContext _context;

    public NotasController(AppDbContext context)
    {
        _context = context;
    }

    [HttpGet]
    public async Task<IActionResult> GetNotas()
    {
        var userId = User.FindFirstValue(ClaimTypes.NameIdentifier);
        // Opcional: filtrar por usuario o retornar todas las notas de prueba
        var notas = await _context.Notas.ToListAsync();
        return Ok(notas);
    }

    [HttpPost]
    public async Task<IActionResult> CreateNote([FromBody] NoteDto dto)
    {
        var userId = User.FindFirstValue(ClaimTypes.NameIdentifier) ?? "default_user";
        
        var note = new Nota 
        { 
            Titulo = dto.Titulo, 
            Contenido = dto.Contenido, 
            FechaCreacion = DateTime.UtcNow 
        };

        _context.Notas.Add(note);
        await _context.SaveChangesAsync();

        return Ok(new { message = "Nota creada exitosamente", note });
    }
}

public class NoteDto
{
    public string Titulo { get; set; } = string.Empty;
    public string Contenido { get; set; } = string.Empty;
}