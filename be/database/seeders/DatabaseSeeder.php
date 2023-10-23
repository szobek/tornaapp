<?php


namespace Database\Seeders;
use Illuminate\Support\Facades\DB;
// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void
    {
        DB::table('users')->insert([
            'name' => 'aa',
            'email' => 'kunszt.norbert@gmail.com',
            'password' => bcrypt('rrrrrr'),
        ]);
    }
}
