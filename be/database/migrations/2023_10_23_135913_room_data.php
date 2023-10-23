<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('room_data', function (Blueprint $table) {
            $table->id();
            $table->integer('room_id');
            $table->string('name');
            $table->string('type');
            $table->boolean('window')->default(0)->change();
            $table->boolean('clima')->default(0)->change();

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('room_data');
    }
};
