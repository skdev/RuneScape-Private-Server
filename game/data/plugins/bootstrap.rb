require 'java'

java_import 'net.scapeemulator.game.button.ButtonHandler'
java_import 'net.scapeemulator.game.command.CommandHandler'

module RuneEmulator
	class Bootstrap
		class << self
			def bind_cmd(name, &block)
				$ctx.add_command_handler(ProcCommandHandler.new(name, block))
			end

			def bind_button(id, &block)
				$ctx.add_button_handler(ProcButtonHandler.new(id, block))
			end
		end

		class ProcButtonHandler < ButtonHandler
			def initialize(id, proc)
				super(id)
				@proc = proc
			end

			def handle(player, slot, parameter)
				@proc.call player, slot, parameter
			end
		end

		class ProcCommandHandler < CommandHandler
			def initialize(name, proc)
				super(name)
				@proc = proc
			end

			def handle(player, args)
				@proc.call player, args
			end
		end
	end
end