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
            
            'email' => 'kunszt.norbert@gmail.com',
            'password' => bcrypt('rrrrrr'),
        ]);
        
        DB::table('user_data')->insert([
            'user_id'=>1,
            'first_name' => 'kunszt',
            'last_name' => 'norbert',
        ]);
        
    }
}
