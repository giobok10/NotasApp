using Microsoft.EntityFrameworkCore;
using NotasApi.Models;

public class AppDbContext : DbContext
{
    public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) { }

    public DbSet<Nota> Notas { get; set; }
}